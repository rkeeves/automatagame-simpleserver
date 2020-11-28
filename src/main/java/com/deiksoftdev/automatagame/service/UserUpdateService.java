package com.deiksoftdev.automatagame.service;

import com.deiksoftdev.automatagame.dto.UserUpdateDTO;
import com.deiksoftdev.automatagame.exception.UserByIdNotRegisteredException;
import com.deiksoftdev.automatagame.model.User;
import com.deiksoftdev.automatagame.model.UserRepository;
import com.deiksoftdev.automatagame.service.validation.UserUpdateDTOValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;

    private final UserUpdateDTOValidator userUpdateDTOValidator;

    public UserUpdateDTO createUserUpdateDTOById(Long id) throws UserByIdNotRegisteredException {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserByIdNotRegisteredException());
        return createUserUpdateDTO(user);
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
    public void updateUser(BindingResult result, UserUpdateDTO userUpdateDTO) {
        final var optionalUser = userRepository.findById(userUpdateDTO.getId());
        if(optionalUser.isEmpty()){
            result.addError(new ObjectError(result.getObjectName(), "User with specified ID not found"));
            return;
        }
        userUpdateDTOValidator.validate(result,userUpdateDTO);
        if(result.hasErrors()){
            return;
        }
        final var user = optionalUser.get();
        user.setName(userUpdateDTO.getName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setAdmin(userUpdateDTO.isAdmin());
        user.setDisabled(userUpdateDTO.isDisabled());
        userRepository.save(user);
    }
}
