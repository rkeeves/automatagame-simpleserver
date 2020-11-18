package com.deiksoftdev.automatagame.users;

import com.deiksoftdev.automatagame.register.UserEmailAlreadyExistsException;
import com.deiksoftdev.automatagame.register.UserNameAlreadyExistsException;
import com.deiksoftdev.automatagame.user.User;
import com.deiksoftdev.automatagame.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User updateUser(long id, UserUpdateDTO userUpdateDTO)
            throws UserNameAlreadyExistsException, UserEmailAlreadyExistsException, UserByIdNotRegisteredException {
        var user = userRepository.findById(id).orElseThrow(()->new UserByIdNotRegisteredException());
        if (!user.getName().equals(userUpdateDTO.getName())) {
            if (nameExists(userUpdateDTO.getName())) {
                throw new UserNameAlreadyExistsException();
            }
            user.setName(userUpdateDTO.getName());
        }
        if (!user.getEmail().equals(userUpdateDTO.getEmail())) {
            if (nameExists(userUpdateDTO.getEmail())) {
                throw new UserEmailAlreadyExistsException();
            }
            user.setEmail(userUpdateDTO.getEmail());
        }
        user.setAdmin(userUpdateDTO.isAdmin());
        user.setDisabled(userUpdateDTO.isDisabled());
        return userRepository.save(user);
    }

    private boolean nameExists(String email) {
        return userRepository.findByName(email) != null;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }
}
