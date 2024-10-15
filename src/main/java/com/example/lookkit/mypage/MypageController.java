// MypageController.java
package com.example.lookkit.mypage;

import com.example.lookkit.user.UserService;
import com.example.lookkit.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;
    private  final UserService userService;


    // 회원정보 조회 페이지
    @GetMapping("/userinfo")
    public String userinfo(Model model, HttpSession session) {
//        int userId = (int)session.getAttribute("userid");
        // 테스트 목적으로 userId를 1로 고정
        int userId = 1;

        UserVO userInfo = mypageService.getUserInfo(userId);
        if (userInfo != null) {
            userInfo.setPassword(""); // 비밀번호를 빈 문자열로 설정
        }
        model.addAttribute("userInfo", userInfo);
        return "mypage/userinfo"; // Thymeleaf 템플릿 이름
    }

    // 회원정보 업데이트 처리
    @PostMapping("/update")
    public String updateUserInfo(@Valid @ModelAttribute MypageDTO userInfo, BindingResult bindingResult, HttpSession session, Model model) {
        // 테스트 목적으로 userId를 1로 고정
        int userId = 1;

        if (bindingResult.hasErrors()) {
            // 검증 오류가 있는 경우, 에러 메시지를 모델에 추가하고 폼 페이지로 돌아갑니다.
            model.addAttribute("message", "입력한 정보에 오류가 있습니다. 다시 확인해주세요.");
            return "mypage/userinfo";
        }

        userInfo.setUserId(userId);

        boolean isUpdated = mypageService.updateUserInfo(userInfo);
        if (isUpdated) {
            model.addAttribute("message", "회원정보가 성공적으로 업데이트되었습니다.");
        } else {
            model.addAttribute("message", "회원정보 업데이트에 실패했습니다.");
        }

        // 업데이트된 정보 다시 조회
//        MypageDTO updatedInfo = mypageService.getUserInfo(userId);
        if (updatedInfo != null) {
            updatedInfo.setPassword(""); // 비밀번호를 빈 문자열로 설정
        }
        model.addAttribute("userInfo", updatedInfo);

        return "mypage/userinfo"; // 같은 페이지로 포워드
    }

    // 이메일 중복 확인 (AJAX 요청)
    @PostMapping("/checkEmail")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email, HttpSession session) {
        // 테스트 목적으로 userId를 1로 고정
        int userId = 1;
        return mypageService.isEmailUnique(email, userId);
    }
}