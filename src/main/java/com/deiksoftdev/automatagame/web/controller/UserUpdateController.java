package com.deiksoftdev.automatagame.web.controller;

import com.deiksoftdev.automatagame.dto.UserUpdateDTO;
import com.deiksoftdev.automatagame.exception.UserByIdNotRegisteredException;
import com.deiksoftdev.automatagame.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @GetMapping("/users/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        try {
            var userUpdateDTO = userUpdateService.createUserUpdateDTOById(id);
            model.addAttribute("userUpdateDTO", userUpdateDTO);
            return "user-update";
        } catch (UserByIdNotRegisteredException e) {
            throw new IllegalArgumentException("User with Id "+ id +" not found.");
        }
    }

    @PostMapping("/users/update/{id}")
    public String processUserUpdateForm(@PathVariable("id") long id, @Valid UserUpdateDTO userUpdateDTO,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return "user-update";
        }
        userUpdateService.updateUser(result,userUpdateDTO);
        if(result.hasErrors()){
            return "user-update";
        }
        return "redirect:/users";
    }
}
