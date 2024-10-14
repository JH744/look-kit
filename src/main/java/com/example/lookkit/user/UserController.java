package com.example.lookkit.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public  void loginForm() {
    }

    @GetMapping("/signUp")
    public void signUpForm() {
    }

    @PostMapping("/signUp")
    public String signUpSubmit(UserVO user){
        System.out.println("---회원가입 제출---");
        System.out.println(user.getUserUuid());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getGender());
        System.out.println(user.getBirthDate());
        System.out.println(user.getEmail());
        System.out.println(user.getPhone());
        System.out.println(user.getAddress());

        boolean result = userService.insertUser(user);
        System.out.println("결과 : " + (result? "성공":"실패"));
        return "redirect:/main";
    };

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
