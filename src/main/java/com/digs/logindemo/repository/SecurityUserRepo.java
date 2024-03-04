package com.digs.logindemo.repository;


import com.digs.logindemo.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityUserRepo extends JpaRepository<SecurityUser, Integer> {

}
