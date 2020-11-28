package com.deiksoftdev.automatagame.service;

import com.deiksoftdev.automatagame.dto.UserUpdateDTO;
import com.deiksoftdev.automatagame.exception.UserByIdNotRegisteredException;
import com.deiksoftdev.automatagame.exception.UserEmailAlreadyExistsException;
import com.deiksoftdev.automatagame.exception.UserNameAlreadyExistsException;
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

    private final UserValidatorService userValidator;

    public UserUpdateDTO createUserUpdateDTOById(Long id) throws UserByIdNotRegisteredException {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserByIdNotRegisteredException());
        return createUserUpdateDTO(user);
    }
    
    @Transactional
    public User updateUser(long id, UserUpdateDTO userUpdateDTO)
            throws UserNameAlreadyExistsException, UserEmailAlreadyExistsException, UserByIdNotRegisteredException {
        var user = userRepository.findById(id).orElseThrow(()->new UserByIdNotRegisteredException());
        if (!user.getName().equals(userUpdateDTO.getName())) {
            userValidator.checkNameExists(userUpdateDTO.getName());
            user.setName(userUpdateDTO.getName());
        }
        if (!user.getEmail().equals(userUpdateDTO.getEmail())) {
            userValidator.checkEmailExists(userUpdateDTO.getEmail());
            user.setEmail(userUpdateDTO.getEmail());
        }
        user.setAdmin(userUpdateDTO.isAdmin());
        user.setDisabled(userUpdateDTO.isDisabled());
        return userRepository.save(user);
    }

    private UserUpdateDTO createUserUpdateDTO(User user){
        var dto = new UserUpdateDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAdmin(user.isAdmin());
        dto.setDisabled(user.isDisabled());
        return dto;
    }

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
