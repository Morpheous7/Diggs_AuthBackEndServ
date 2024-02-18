package com.ddr.authenticatedbackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author Ike Kennedy
 */

@Data
@Builder
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    private String username;
    private String password;

    private String authorities;


    public User (Integer id, String username, String password, String authorities) {
        super();
        this.userId = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User (String username, String password, String authorities) {
        super();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User() {super();    }

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    public  Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (String s : User.getAuthority().toString().split(",")) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(s);
            list.add(simpleGrantedAuthority);
        }
        return list;
    }

    protected static Object getAuthority() {
        return  setAuthority("ROLE_ADMIN", "ROLE_USER");
    }

    private static Collection<SimpleGrantedAuthority> setAuthority(String role_admin, String role_user) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role_admin);
        SimpleGrantedAuthority simpGrantedAuthority = new SimpleGrantedAuthority(role_user);
        list.add(simpleGrantedAuthority); list.add(simpGrantedAuthority);
        return list;
    }


    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */

    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
