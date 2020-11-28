package com.deiksoftdev.automatagame.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingPageController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
