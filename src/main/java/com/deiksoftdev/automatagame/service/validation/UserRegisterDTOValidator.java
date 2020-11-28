package com.deiksoftdev.automatagame.service.validation;

import com.deiksoftdev.automatagame.dto.UserRegisterDTO;
import com.deiksoftdev.automatagame.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRegisterDTOValidator{

    private final UserRepository userRepository;

    @Transactional
    public void validate(BindingResult result, UserRegisterDTO dto) {
        if(isNameExisting(dto.getName())){
            result.addError(new FieldError(result.getObjectName(), "name", dto.getName(), true, null, null, "name exists already"));
        }
        if(isEmailExisting(dto.getEmail())){
            result.addError(new FieldError(result.getObjectName(), "email", dto.getEmail(), true, null, null, "email exists already"));
        }
    }

    @Transactional
    public boolean isNameExisting(String name) {
        return userRepository.findByName(name) != null;
    }

    @Transactional
    public boolean isEmailExisting(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Transactional
    public List<FieldError> validate(UserRegisterDTO dto) {
        List<FieldError> result = new ArrayList<>();
        if(isNameExisting(dto.getName())){
            result.add(new FieldError("user", "name", "name exists already"));
        }
        if(isEmailExisting(dto.getEmail())){
            result.add(new FieldError("user", "email", "email exists already"));
        }
        return result;
    }
}
