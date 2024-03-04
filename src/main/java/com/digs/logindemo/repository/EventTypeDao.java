package com.digs.logindemo.repository;


import com.digs.logindemo.model.Event_type;
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

/**
 * @author Ike Kennedy
 */

@Repository
public class EventTypeDao implements JpaRepository<Event_type, Integer> {

    @Override
    public void flush() {

    }

    @Override
    public <S extends Event_type> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Event_type> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Event_type> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    public Event_type getOne(Integer integer) {
        return null;
    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    public Event_type getById(Integer integer) {
        return null;
    }

    @Override
    public Event_type getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Event_type> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Event_type> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Event_type> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Event_type> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Event_type> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Event_type> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Event_type, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Event_type> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Event_type> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Event_type> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Event_type> findAll() {
        return null;
    }

    @Override
    public List<Event_type> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Event_type entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Event_type> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Event_type> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Event_type> findAll(Pageable pageable) {
        return null;
    }
}
