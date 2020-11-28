package com.deiksoftdev.automatagame.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByName(String name);

    User findByEmail(String email);
}
