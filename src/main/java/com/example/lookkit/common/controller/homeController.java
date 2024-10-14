package com.example.lookkit.common.controller;

import com.example.lookkit.user.CustomUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/main")
    public String mainPage(Authentication auth, HttpSession session){
        if(auth != null){
            System.out.println(auth);
            CustomUser user = (CustomUser) auth.getPrincipal();
            session.setAttribute("userid",user.getUserId());
            // Session의 유효 시간 설정 (3600초 = 60분)
            session.setMaxInactiveInterval(3600);
//            System.out.println(user.getUserId());
//            System.out.println(auth.getName());
//            System.out.println(auth.getAuthorities());
//            System.out.println(auth.isAuthenticated());
        }
        return "/home/home.html";
    }

}
