package com.deiksoftdev.automatagame.web.controller;

import com.deiksoftdev.automatagame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ScoreListController {

    private final UserService userService;

    @GetMapping("/score/{scenario}")
    public String showHighScoreList(@PathVariable("scenario") String scenario, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("scenario", scenario);
        return "score-list";
    }
}
