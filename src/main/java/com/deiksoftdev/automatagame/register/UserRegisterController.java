package com.deiksoftdev.automatagame.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @Autowired
    public UserRegisterController(UserRegisterService userRegisterService) {
        super();
        this.userRegisterService = userRegisterService;
    }

    @GetMapping("/register")
    public String showUserRegisterForm(Model model) {
        final var userDto = new UserRegisterDTO();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView processUserRegisterForm(
            @ModelAttribute("user") @Valid UserRegisterDTO userDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", userDto);
        }
        try {
            var registered = userRegisterService.registerNewUserAccount(userDto);
        } catch (UserNameAlreadyExistsException nameExistsEx) {
            ModelAndView mav = new ModelAndView("register", "user", userDto);
            result.addError(new FieldError("user", "name", "name exists already"));
            mav.addAllObjects(result.getModel());
            return mav;
        } catch (UserEmailAlreadyExistsException emailExistsEx) {
            ModelAndView mav = new ModelAndView("register", "user", userDto);
            result.addError(new FieldError("user", "email", "email exists already"));
            mav.addAllObjects(result.getModel());
            return mav;
        }
        return new ModelAndView("registerSuccess", "user", userDto);
    }
}
