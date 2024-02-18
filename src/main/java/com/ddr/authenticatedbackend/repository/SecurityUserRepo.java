package com.ddr.authenticatedbackend.repository;
/*
 * @author Ike on 2/16/2024
 */

import com.ddr.authenticatedbackend.model.Event;
import com.ddr.authenticatedbackend.model.SecurityUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface SecurityUserRepo extends JpaRepository<SecurityUser, Integer> {

}
