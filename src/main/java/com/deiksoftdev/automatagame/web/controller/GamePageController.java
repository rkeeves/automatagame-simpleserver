package com.deiksoftdev.automatagame.web.controller;

import com.deiksoftdev.automatagame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class GamePageController {

    private final UserService userService;

    @RequestMapping("/game/springtest")
    public ModelAndView showGamePage(Model model) {
        final var user = userService.findByName( SecurityContextHolder.getContext().getAuthentication().getName());
        return new ModelAndView("springCommunicationTest", "currentUser", user);
    }
}
