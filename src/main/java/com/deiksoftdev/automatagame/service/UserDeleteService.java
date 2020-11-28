package com.deiksoftdev.automatagame.service;

import com.deiksoftdev.automatagame.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;

    public void deleteUser(long id) {
        final var optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            return;
        }
        final var user = optionalUser.get();
        if(!user.isAdmin()){
            userRepository.delete(user);
        }

    }
}
