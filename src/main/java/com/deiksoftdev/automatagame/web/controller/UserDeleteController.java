package com.deiksoftdev.automatagame.web.controller;

import com.deiksoftdev.automatagame.service.UserDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @GetMapping("/users/delete/{id}")
    public String processUserDelete(@PathVariable("id") long id) {
        userDeleteService.deleteUser(id);
        return "redirect:/users";
    }
}
