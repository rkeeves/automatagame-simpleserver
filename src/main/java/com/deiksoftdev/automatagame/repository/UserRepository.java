package com.deiksoftdev.automatagame.repository;

import org.springframework.data.repository.CrudRepository;
import com.deiksoftdev.automatagame.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
