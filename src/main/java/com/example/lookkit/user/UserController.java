package com.example.lookkit.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "redirect:/auth/login";
    };

    @GetMapping("/findID")
    public void findID() {
    }

    @PostMapping("/findID")
    public String submitFindID(String name, String email, Model model) {
        System.out.println("전달받은 이름: "+name);
        System.out.println("전달받은 멜: "+ email);
        String userId = userService.findUserUuidByNameAndEmail(name,email);
        if (userId == null) {
            System.out.println("아이디가 null로 확인");
            model.addAttribute("errorMessage", "일치하는 사용자를 찾을 수 없습니다.");
            return "redirect:/auth/findID";  // 에러 메시지를 유지하며 다시 아이디 찾기 페이지로 이동
        }
        model.addAttribute("userid", userId);
        model.addAttribute("userName", name);
//        return "redirect:/auth/findIdOK";
        return "/auth/findIdOK";
    }

    @GetMapping("/findIdOK")
    public void findIdOK() {
    }
    @GetMapping("/findPwd")
    public void findPwd() {
    }

    // 비밀번호 변경요청
    @PostMapping("/updatePwd")
    @ResponseBody
    public String updatePassword(@RequestBody UserVO user){
      return  userService.updatePassword(user.getUserUuid(),user.getPassword());
    }

    // 이름, 이메일로 유저 아이디찾기 api
    @PostMapping("/findUser")
    @ResponseBody
    public String findUserByNameAndEmail(@RequestBody UserVO user) {
        String userId = userService.findUserUuidByNameAndEmail(user.getUserName(),user.getEmail());
        if (userId == null) {
            System.out.println("아이디가 null로 확인");
            return "fail";
        }
        return userId;
    }


    @GetMapping("/checkDuplicateId")
    @ResponseBody
    public boolean checkDuplicateId(@RequestParam String id){
        System.out.println("중복체크 컨트롤러 전달"+id);
        UserVO user =  userService.getUserByUuid(id);
        //가져온 유저객체가 null이면 중복값이 없으므로 true로 반환.
        return (user == null);
    }





}
