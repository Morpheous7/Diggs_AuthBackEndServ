package com.ddr.authenticatedbackend.service;

import com.ddr.authenticatedbackend.model.LoginResponseDTO;
import com.ddr.authenticatedbackend.model.SecurityUser;
import com.ddr.authenticatedbackend.model.User;
import com.ddr.authenticatedbackend.repository.SecUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ike Kennedy
 */
@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {
    private BCryptPasswordEncoder passwordEncoder;
    private TokenService tokenService;
    private SecUserRepo userRepository;
    private UserService userService;
    JwtEncoder jwtEncoder;

    public AuthenticationService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userRepository = new SecUserRepo();

        this.tokenService = new TokenService(this.jwtEncoder);
        this.userService = new UserService(this.passwordEncoder, this.userRepository);
    }

    public User registerUser(String username, String password) {

        String encodedPassword = "BCrypt" + this.passwordEncoder.encode(password);
        User appUser = User.builder()
                .username(username)
                .password(encodedPassword)
                .authorities("ROLE_USER")
                .build();
        this.userRepository.save(appUser);
        return this.userRepository.saveNewUser(appUser);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        String encodedPassword = "BCrypt" + this.passwordEncoder.encode(password);
        String token = "";
        LoginResponseDTO loginResponseDTO = null;
        User appUser = this.userRepository.findByUsername(username);

        if (appUser == null) {
            appUser = this.userRepository.findByUsernameAndAndPassword(username, password);
        }

        if (appUser.getAuthorities() == null) {
            appUser.setAuthorities("ROLE_USER");
        }
        SecurityUser securityUser = new SecurityUser(appUser);
        Authentication auth = UsernamePasswordAuthenticationToken.authenticated(username,
                encodedPassword, securityUser.getAuthorities());
        token += this.tokenService.generateJwt(auth);
        loginResponseDTO = new LoginResponseDTO(securityUser, token);
        return loginResponseDTO;
    }

/*        System.out.println("User is not registered and therefore cannot login");
        System.out.println("User is not registered and therefore cannot login");

        System.out.println("Will attempt to register new user... ");
        System.out.println("Will attempt to register new user:  ... ");

        //Registering new user
        registerNewUser(username, password);
        System.out.println("Registered new user:  ... ");
        //Check to see if user is present in repository
        System.out.println("Checking repo to confirm new user registration. Please wait!  ... ");

        User testUser = isUserPresent(username, password);

       SecurityUser securityUser  = new SecurityUser(testUser);
       Authentication auth = UsernamePasswordAuthenticationToken.authenticated(username,
               encodedPassword, securityUser.getAuthorities());

       token += this.tokenService.generateJwt(auth);
       loginResponseDTO = new LoginResponseDTO(securityUser, token);
       return loginResponseDTO;
    }*/

    private void registerNewUser(String username, String password) {
        String encodedPassword = "BCrypt" + this.passwordEncoder.encode(password);
        User appUser = com.ddr.authenticatedbackend.model.User.builder()
                .username(username)
                .password(encodedPassword)
                .authorities("ROLE_USER")
                .build();
        this.userRepository.save(appUser);
        new SecurityUser(this.userRepository.saveNewUser(appUser));
    }

    private User isUserPresent(String username, String password) {
        String encodedPassword = "BCrypt" + this.passwordEncoder.encode(password);

        User appUser = null;
        appUser = this.userRepository.findByUsernameAndAndPassword(username, encodedPassword);
        SecurityUser securityUser = new SecurityUser(appUser);
        if ((appUser == null) || (securityUser.user == null)) {
            securityUser.setUser(this.userRepository.findByUsernameAndAndPassword(username, encodedPassword));
        }
        return securityUser.user;
    }
}