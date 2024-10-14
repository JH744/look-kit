package com.example.lookkit.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/main")
    public String mainPage(Authentication auth){
        if(auth != null){
            System.out.println(auth);
            System.out.println(auth.getName());
            System.out.println(auth.getCredentials());
            System.out.println(auth.getAuthorities());
            System.out.println(auth.getDetails());
            System.out.println(auth.isAuthenticated());
        }
        return "/home/home.html";
    }

}
