package com.example.lookkit.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/checkDuplicateId")
    @ResponseBody
    public boolean checkDuplicateId(@RequestParam String id){
        System.out.println("중복체크 컨트롤러 전달"+id);
        UserVO user =  userService.getUserByUuid(id);
        //가져온 유저객체가 null이면 중복값이 없으므로 true로 반환.
        boolean result = (user == null);
        return result;
    }









}
