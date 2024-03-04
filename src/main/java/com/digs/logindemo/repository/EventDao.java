package com.digs.logindemo.repository;


import com.digs.logindemo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ike Kennedy
 */

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {

}
