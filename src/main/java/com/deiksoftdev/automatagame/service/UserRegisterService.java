package com.deiksoftdev.automatagame.service;

import com.deiksoftdev.automatagame.dto.UserRegisterDTO;
import com.deiksoftdev.automatagame.model.User;
import com.deiksoftdev.automatagame.model.UserRepository;
import com.deiksoftdev.automatagame.service.validation.UserRegisterDTOValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final SequenceGeneratorService sequenceGenerator;

    private final UserRegisterDTOValidator userRegisterDTOValidator;

    public UserRegisterDTO createUserRegisterDTO() {
        return new UserRegisterDTO();
    }

    @Transactional
    public void createUser(BindingResult result, UserRegisterDTO userRegisterDTO) {
        createUser(result,userRegisterDTO, false);
    }

    @Transactional
    public void createUser(BindingResult result, UserRegisterDTO userRegisterDTO, boolean isAdmin) {
        userRegisterDTOValidator.validate(result,userRegisterDTO);
        if(result.hasErrors())
            return;
        saveUser(userRegisterDTO,isAdmin);
    }

    @Transactional
    public List<FieldError> createUser(UserRegisterDTO userRegisterDTO, boolean isAdmin) {
        List<FieldError> errors = userRegisterDTOValidator.validate(userRegisterDTO);
        if(!errors.isEmpty())
            return errors;
        saveUser(userRegisterDTO,isAdmin);
        return errors;
    }

    private User saveUser(UserRegisterDTO dto, boolean isAdmin){
        return saveUser(dto.getName(),dto.getPassword(),dto.getEmail(),isAdmin);
    }

    private User saveUser(String name, String password,String email, boolean isAdmin){
        var user = new User();
        user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setAdmin(isAdmin);
        var scenarioHighScores = new HashMap<String,Integer>();
        scenarioHighScores.put("dummy",1);
        user.setScores(scenarioHighScores);
        return userRepository.save(user);
    }
}
