package com.deiksoftdev.automatagame.service.validation;

import com.deiksoftdev.automatagame.dto.UserUpdateDTO;
import com.deiksoftdev.automatagame.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class UserUpdateDTOValidator{

    private final UserRepository userRepository;

    @Transactional
    public void validate(BindingResult result, UserUpdateDTO dto) {
        if(isNameExisting(dto.getId(),dto.getName())){
            result.addError(new FieldError(result.getObjectName(), "name", dto.getName(), true, null, null,"name exists already"));
        }
        if(isEmailExisting(dto.getId(),dto.getEmail())){
            result.addError(new FieldError(result.getObjectName(), "email", dto.getEmail(), true, null, null,"email exists already"));
        }
    }

    @Transactional
    public boolean isNameExisting(Long id, String newName) {
        var user = userRepository.findByName(newName);
        if(user==null)
            return false;
        return !user.getId().equals(id);
    }

    @Transactional
    public boolean isEmailExisting(Long id, String newEmail) {
        var user = userRepository.findByEmail(newEmail);
        if(user==null)
            return false;
        return !user.getId().equals(id);
    }
}
