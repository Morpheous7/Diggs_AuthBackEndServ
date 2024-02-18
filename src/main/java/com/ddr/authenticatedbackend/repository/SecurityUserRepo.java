package com.ddr.authenticatedbackend.repository;
/*
 * @author Ike on 2/16/2024
 */

import com.ddr.authenticatedbackend.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityUserRepo extends JpaRepository<SecurityUser, Integer> {

}
