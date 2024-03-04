package com.digs.logindemo.service;


import com.digs.logindemo.model.LoginResponseDTO;
import com.digs.logindemo.model.SecurityUser;
import com.digs.logindemo.model.User;
import com.digs.logindemo.repository.SecUserRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ike Kennedy
 */
@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService  implements UserDetailsService, UserDetailsManager {
    private BCryptPasswordEncoder passwordEncoder;
    private TokenService tokenService;
    private SecUserRepo userRepository;
    private JwtEncoder jwtEncoder;
    private static User user;
    private SecurityUser secUser;

    public AuthenticationService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userRepository = new SecUserRepo();
        this.secUser = new SecurityUser(user);
        this.tokenService = new TokenService(this.jwtEncoder);
    }

    public void registerUser(User newuser) {

        user = User.builder()
                .username(newuser.getUsername())
                .password(newuser.getPassword())
                .email(newuser.getEmail())
                .phn(newuser.getPhn())
                .authorities("ROLE_USER")
                .build();

        this.userRepository.save(user);
        this.secUser.setUser(this.userRepository.saveNewUser(user));
    }

    public LoginResponseDTO loginUser(@NonNull String username, @NonNull String password) {
        String token = "";
        LoginResponseDTO loginResponseDTO = null;
        user = this.userRepository.findByUsername(username);

        if (user == null) {
            user = this.userRepository.findByUsernameAndAndPassword(username, password);
        }

        if (user.getAuthorities() == null) {
            user.setAuthorities("ROLE_USER");
        }
        this.secUser = new SecurityUser(user);
        Authentication auth = UsernamePasswordAuthenticationToken.authenticated(username,
                password, this.secUser.getAuthorities());
        token += this.tokenService.generateJwt(auth);
        loginResponseDTO = new LoginResponseDTO(this.secUser, token);
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
        User appUser = User.builder()
                .username(username)
                .password(encodedPassword)
                .authorities("ROLE_USER")
                .build();
        this.userRepository.save(appUser);
        new SecurityUser(this.userRepository.saveNewUser(appUser));
    }
    public Boolean isUserPresent(User ur) {
        user = this.userRepository.findByUsernameAndAndPassword(ur.getUsername(), ur.getPassword());
        if(user != null && user.equals(ur))
            System.out.println("It's a match and "+ user +" is equal to "+ ur);

        if(user == null || user.getEmail() == null || user.getPassword() == null ||
                user.getUsername()==null || user.getPhn()==null) {
            user = this.userRepository.findByEmail(ur.getEmail());
            if(user == null)
                this.userRepository.save(ur);
            return user != null && user.getEmail() != null && user.getPassword() != null &&
                    user.getUsername() != null && user.getPhn() != null;

        } else  {
                this.secUser = new SecurityUser(user);
                return true;
        }
    }

    public Boolean isUserPresent(String username, String password) {
        User appUser = this.userRepository.findByUsernameAndAndPassword(username, password);
        if(appUser == null || appUser.getEmail() == null || appUser.getPassword() == null ||
        appUser.getUsername()==null || appUser.getPhn()==null)
            return false;
        else
            this.secUser = new SecurityUser(appUser);
        return true;
    }

    /**
     * Create a new user with the supplied details.
     *
     * @param user
     */
    @Override
    public void createUser(UserDetails user) {

    }

    /**
     * Update the specified user.
     *
     * @param user
     */
    @Override
    public void updateUser(UserDetails user) {

    }

    /**
     * Remove the user with the given login name from the system.
     *
     * @param username
     */
    @Override
    public void deleteUser(String username) {

    }

    /**
     * Modify the current user's password. This should change the user's password in the
     * persistent user repository (database, LDAP etc).
     *
     * @param oldPassword current password (for re-authentication if required)
     * @param newPassword the password to change to
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    /**
     * Check if a user with the supplied login name exists in the system.
     *
     * @param username
     */
    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user= this.userRepository.findByEmail(username);
        this.secUser.setUser(user);
        return this.secUser;
    }
}