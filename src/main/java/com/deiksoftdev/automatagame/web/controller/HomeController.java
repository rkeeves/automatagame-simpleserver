package com.deiksoftdev.automatagame.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView showUserHome(Model model) {
        return new ModelAndView("home", "user", model);
    }
}
