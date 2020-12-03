package com.deiksoftdev.automatagame.web.controller;

import com.deiksoftdev.automatagame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserListController {

    private final UserService userService;

    @Autowired
    public UserListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsersList(Model model, @RequestParam(defaultValue = "") String name) {
        model.addAttribute("users", userService.findAllLike(name));
        return "user-list";
    }
}
