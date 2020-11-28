package com.deiksoftdev.automatagame.service;

import com.deiksoftdev.automatagame.model.User;
import com.deiksoftdev.automatagame.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }

    @Transactional
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
