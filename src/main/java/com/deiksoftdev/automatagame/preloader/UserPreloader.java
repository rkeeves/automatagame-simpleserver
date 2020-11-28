package com.deiksoftdev.automatagame.preloader;

import com.deiksoftdev.automatagame.dto.UserRegisterDTO;
import com.deiksoftdev.automatagame.model.User;
import com.deiksoftdev.automatagame.service.UserRegisterService;
import com.deiksoftdev.automatagame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserPreloader implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(UserPreloader.class);

    boolean alreadySetup = false;

    private final UserService userService;

    private final UserRegisterService userRegisterService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        logger.info("Try preloading default admin");
        createUserIfAbsent("admin", "admin@admin.com", "admin", true);
        logger.info("Try preloading default user");
        createUserIfAbsent("user", "user@user.com", "user", false);
        alreadySetup = true;
    }

    @Transactional
    void createUserIfAbsent(String name, String email, String password, boolean isAdmin){
        User user = userService.findByName(name);
        if(user!=null) {
            logger.warn("User entity with name: {}, already exists", name);
            return;
        }
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setName(name);
        dto.setEmail(email);
        dto.setPassword(password);
        List<FieldError> errors = userRegisterService.createUser(dto,isAdmin);
        if(errors.isEmpty()){
            logger.warn("Created user entity with name: {}, isAdmin: {}",name, isAdmin);
            return;
        }
        logger.error("Wasn't able to create user entity with name: {}, isAdmin: {}",name, isAdmin);
        logger.error("Validation errors occurred");
        errors.stream()
                .map(Object::toString)
                .forEach(logger::error);
    }
}