package com.deiksoftdev.automatagame.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deiksoftdev.automatagame.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
