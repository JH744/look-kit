package com.example.lookkit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/main")
    public String mainPage(){
        return "/home/home.html";
    }

}
