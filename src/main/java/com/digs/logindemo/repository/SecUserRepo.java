package com.digs.logindemo.repository;



import com.digs.logindemo.model.SecurityUser;
import com.digs.logindemo.model.User;
import lombok.NonNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class SecUserRepo implements UserRepository {

    User user;
    List<User> userList;


    public SecUserRepo() {
        this.user = User.builder().build();
    }

    @Override
    public User findByUsernameAndAndPassword(@NonNull String username, @NonNull String password) {
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return user;
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }



    @Override
    public void flush() {

    }


    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return (List<S>) userList;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

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
    public User getOne(Integer integer) {
        return user;
    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    public User getById(Integer integer) {
        return user;
    }

    @Override
    public User getReferenceById(Integer integer) {
        return user;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


    @Override
    public <S extends User> S save(S entity) {
        return entity;
    }

    public User saveNewUser(User user) {
        this.user = user;
        return this.user;
    }


    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.ofNullable(user);
    }

    @Override
    public <S extends SecurityUser> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return (List<S>) userList;
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public List<User> findAllById(Iterable<Integer> integers) {
        return userList;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }


    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }


    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> findAll(Sort sort) {
        return userList;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return (Page<User>) userList;
    }
}
