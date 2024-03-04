package com.digs.logindemo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private String email;

    private String authorities;
    private String phn;


    public User (Integer id, String username, String password, String email, String authorities, String phn) {
        super();
        this.userId = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.phn = phn;
    }

    public User (String username, String password, String email, String authorities,String phn) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.phn = phn;
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
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", authorities='" + authorities + '\'' +
                ", phn='" + phn + '\'' +
                '}';
    }
}
