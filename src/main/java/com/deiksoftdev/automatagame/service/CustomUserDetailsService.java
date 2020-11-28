package com.deiksoftdev.automatagame.service;

import com.deiksoftdev.automatagame.config.security.CustomUserDetails;
import com.deiksoftdev.automatagame.model.User;
import com.deiksoftdev.automatagame.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return new CustomUserDetails(user);
    }
}
