package com.ddr.authenticatedbackend.service;

import com.ddr.authenticatedbackend.model.SecurityUser;
import com.ddr.authenticatedbackend.model.User;
import com.ddr.authenticatedbackend.model.LoginResponseDTO;
import com.ddr.authenticatedbackend.repository.UserRepository;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ike Kennedy
 */
@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    public User registerUser(String username, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        User appUser = com.ddr.authenticatedbackend.model.User.builder()
                .username(username)
                .password(encodedPassword)
                .authorities("USER")
                .build();
        if(userRepository.findByUsername(username).isPresent())
            return userRepository.findByUsername(username).get();
        userRepository.save(appUser);

       return userRepository.save(appUser);
    }

    public LoginResponseDTO loginUser(String username, String password) throws InstantiationException, IllegalAccessException {
        String encodedPassword = "BCrypt" + passwordEncoder.encode(password);
        String token = "";
        LoginResponseDTO loginResponseDTO = null;
        if (userRepository.findByUsername(username).isPresent()) {
            User appUser = userRepository.findByUsername(username).get();
            if(appUser.getAuthorities() == null)
                appUser.setAuthorities("USER");

            SecurityUser securityUser  = new SecurityUser(appUser);
            Authentication auth = UsernamePasswordAuthenticationToken.authenticated(username,
                    encodedPassword, securityUser.getAuthorities());

            token += tokenService.generateJwt(auth);
            loginResponseDTO = new LoginResponseDTO(securityUser, token);
            return loginResponseDTO;
        }

            System.out.println("User is not registered and therefore cannot login");
            System.out.println("User is not registered and therefore cannot login");
            System.out.println("User is not registered and therefore cannot login");
            throw new IOException(Throwable.class.newInstance().getMessage());
    }
}