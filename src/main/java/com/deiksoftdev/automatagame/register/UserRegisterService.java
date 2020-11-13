package com.deiksoftdev.automatagame.register;

import com.deiksoftdev.automatagame.user.User;
import com.deiksoftdev.automatagame.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegisterService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerNewUserAccount(UserRegisterDTO userRegisterDTO)
            throws UserNameAlreadyExistsException, UserEmailAlreadyExistsException {
        if (nameExists(userRegisterDTO.getName())) {
            throw new UserNameAlreadyExistsException();
        }
        if (emailExists(userRegisterDTO.getEmail())) {
            throw new UserEmailAlreadyExistsException();
        }
        var user = new User();
        user.setName(userRegisterDTO.getName());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setEmail(userRegisterDTO.getEmail());
        user.setAdmin(false);
        user = userRepository.save(user);
        return user;
    }

    private boolean nameExists(String email) {
        return userRepository.findByName(email) != null;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
