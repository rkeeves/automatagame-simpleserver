package com.deiksoftdev.automatagame.security;

import com.deiksoftdev.automatagame.model.User;
import com.deiksoftdev.automatagame.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is a short lived class until a standalone db will be in place.
 */
@Component
public class SecurityDataPreloader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityDataPreloader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        getUserCreateIfAbsent("user", "user@user.com", "user", false);
        getUserCreateIfAbsent("admin", "admin@admin.com", "admin", true);
        alreadySetup = true;
    }

    @Transactional
    User getUserCreateIfAbsent(String name, String email, String password, boolean isAdmin) {
        var user = userRepository.findByName(name);
        if (user == null) {
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setAdmin(isAdmin);
            return userRepository.save(user);
        }
        return user;
    }
}