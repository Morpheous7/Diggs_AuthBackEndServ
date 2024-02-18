package com.ddr.authenticatedbackend.service;


import com.ddr.authenticatedbackend.model.SecurityUser;
import com.ddr.authenticatedbackend.model.User;
import com.ddr.authenticatedbackend.repository.SecUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


/**
 * @author Ike Kennedy
 */


@Service
public class UserService implements UserDetailsService, UserDetailsManager {

    public SecUserRepo userRepository;
    private BCryptPasswordEncoder encoder;
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private SecurityUser[] securityUser;

    public UserService() {
        this.userRepository = new SecUserRepo();
        this.encoder = new BCryptPasswordEncoder();
        this.securityUser = preRegUsers();
        this.inMemoryUserDetailsManager = new InMemoryUserDetailsManager(this.securityUser);
    }


    public UserService(BCryptPasswordEncoder endoer, SecUserRepo secUserRepo) {
        this.userRepository = secUserRepo;
        this.encoder = endoer;
        this.securityUser = preRegUsers();
        this.inMemoryUserDetailsManager = new InMemoryUserDetailsManager(this.securityUser);
    }

    private SecurityUser[] preRegUsers() {
        User user1 = User.builder()
                .username("ethan")
                .password("password")
                .authorities("ROLE_USER")
                .build();
        User user2 = User.builder().username("ikenna")
                .password("pas")
                .authorities("ROLE_USER")
                .build();
        User admin = User.builder()
                .username("mike")
                .password("adminPass")
                .authorities("ROLE_ADMIN")
                .build();
        SecurityUser securityUser0 = new SecurityUser(user1);
        SecurityUser securityUser1 = new SecurityUser(user2);
        SecurityUser securityUser2 = new SecurityUser(admin);
        SecurityUser[] secArray = new SecurityUser[]{securityUser0, securityUser1, securityUser2};
        return secArray;
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
        System.out.println("In the user details service");
        SecurityUser secUser;
        User appUser = null;
        appUser = this.userRepository.findByUsername(username);
        if (appUser != null) {
            //return new SecurityUser(appUser );
            if (appUser.getUsername().equals(username)) {
                System.out.println("User-details service found user is now returning this username : " + username);
                return secUser = new SecurityUser(appUser);
            }
            System.out.println("User-details service returns: " + "User doesn't exist yet!");
        } else {
            System.out.println("User-details service will to create and load this new user: ");
            appUser = User.builder().username(username).authorities("ROLE_USER").build();
            return secUser = new SecurityUser(this.userRepository.save(appUser));
        }
        System.out.println("User-details service getting here is a sing of TROUBLE ");

        return secUser = new SecurityUser();
    }

    public InMemoryUserDetailsManager userDetailsService(SecurityUser[] secArray) {
        for (SecurityUser user : secArray) {
            createUser(user);
        }
        return new InMemoryUserDetailsManager(secArray);
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
}
