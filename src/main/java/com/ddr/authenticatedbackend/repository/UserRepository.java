package com.ddr.authenticatedbackend.repository;

import com.ddr.authenticatedbackend.model.SecurityUser;
import com.ddr.authenticatedbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ike Kennedy
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findByUsernameAndAndPassword(String username, String password);

    User findByUsername(String username);

    <S extends User> S saveAndFlush(S entity);

    <S extends SecurityUser> S save(S entity);
}
