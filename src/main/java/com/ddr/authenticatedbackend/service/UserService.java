package com.ddr.authenticatedbackend.service;


import com.ddr.authenticatedbackend.model.SecurityUser;
import com.ddr.authenticatedbackend.model.User;
import com.ddr.authenticatedbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author Ike Kennedy
 */


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
/*

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("ethan")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("ike")
                .password(passwordEncoder().encode("kenn"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("mike")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
*/


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
        if(userRepository.findByUsername(username).isPresent()) {
            User appUser = userRepository.findByUsername(username).get();
            System.out.println("User-details service found user is now returning this username : "+ username);
            return new SecurityUser(appUser );
        }else {
            System.out.println("User-details service returns: "+ "User doesn't exist yet!");
            System.out.println("User-details service will create and load this new user: ");
            User appUser = User.builder().username(username).build();
            return userRepository.findByUsername(username)
                    .map(user -> new SecurityUser(appUser))
                    .orElseThrow(() -> new UsernameNotFoundException("User is not valid!"));
        }
    }
}
