package com.deiksoftdev.automatagame.web;

import com.deiksoftdev.automatagame.dto.UserRegisterDTO;
import com.deiksoftdev.automatagame.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @GetMapping("/register")
    public String showUserRegisterForm(Model model) {
        final var userDto = userRegisterService.createUserRegisterDTO();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String processUserRegisterForm(
            @ModelAttribute("user") @Valid UserRegisterDTO userDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userRegisterService.createUser(result,userDto);
        if(result.hasErrors()){
            return "register";
        }
        return "registerSuccess";
    }
}
