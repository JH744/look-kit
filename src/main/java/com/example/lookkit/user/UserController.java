package com.example.lookkit.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserController {
    @GetMapping("/login")
    public  void login() {
    }
    @GetMapping("/signUp")
    public void signUp() {
    }
    @GetMapping("/findID")
    public void findID() {
    }
    @GetMapping("/findIdOK")
    public void findIdOK() {
    }
    @GetMapping("/findPwd")
    public void findPwd() {
    }

}
