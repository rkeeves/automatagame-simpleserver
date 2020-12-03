package com.deiksoftdev.automatagame.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByName(String name);

    User findByEmail(String email);

    List<User> findByNameLike(String name);
}
