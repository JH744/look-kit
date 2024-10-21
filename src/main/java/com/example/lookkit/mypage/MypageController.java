package com.example.lookkit.mypage;

import com.example.lookkit.user.CustomUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/enter-password")
    public String enterPassword(Model model, HttpSession session){
        System.out.println("확인>>>>>");
        long userId = (long) session.getAttribute("userid");
        MypageDTO userInfo = mypageService.getUserInfo(userId);
        System.out.println("아이디 확인 : " + userId);
        model.addAttribute("userInfo", userInfo);
         //return "mypage/pw_check_popup_modal";
         return "mypage/pw_check_modal";
    }

    // 회원정보 조회 페이지
    @GetMapping("/userinfo")
    public String userinfo(Model model, HttpSession session) {
        System.out.println("확인>>>>>");
        long userId = (long) session.getAttribute("userid");
        System.out.println("아이디 확인 : " + userId);

        MypageDTO userInfo = mypageService.getUserInfo(userId);
        model.addAttribute("userInfo", userInfo);
        return "mypage/userinfo"; // Thymeleaf 템플릿 이름
    }

    // 회원정보 업데이트 처리
    @PostMapping("/update")
    public String updateUserInfo(@Valid @ModelAttribute("userInfo") MypageDTO mypageDTO, BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long userId = customUser.getUserId();

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "입력한 정보에 오류가 있습니다. 다시 확인해주세요.");
            return "mypage/userinfo";
        }

        // 이메일 중복 확인
        if (mypageService.isEmailDuplicate(mypageDTO.getEmail(), userId)) {
            model.addAttribute("message", "이미 사용 중인 이메일입니다.");
            return "mypage/userinfo";
        }

        mypageDTO.setUserId(userId);

        boolean isUpdated = mypageService.updateUserInfo(mypageDTO);
        if (isUpdated) {
            model.addAttribute("message", "회원정보가 성공적으로 업데이트되었습니다.");
        } else {
            model.addAttribute("message", "회원정보 업데이트에 실패했습니다.");
        }

        // 업데이트된 정보 다시 조회
        MypageDTO updatedInfo = mypageService.getUserInfo(userId);
        model.addAttribute("userInfo", updatedInfo);

        return "mypage/userinfo"; // 같은 페이지로 포워드
    }

    // 이메일 중복 확인 (AJAX)
    @GetMapping("/checkDuplicateEmail")
    @ResponseBody
    public boolean checkDuplicateEmail(@RequestParam String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            return false;
        }
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long userId = customUser.getUserId();

        boolean isDuplicate = mypageService.isEmailDuplicate(email, userId);
        return !isDuplicate; // true if email is available, false if duplicate
    }

    @PostMapping("/verifyPassword")
    @ResponseBody
    public Map<String, Object> verifyPassword(@RequestParam String currentPassword, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("verifyPassword 호출됨"); // 로그 추가
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long userId = customUser.getUserId();
        String storedPassword = mypageService.getPassword(userId);

        if (storedPassword == null) {
            response.put("success", false);
            response.put("message", "사용자 정보를 찾을 수 없습니다.");
            return response;
        }

        boolean matches = passwordEncoder.matches(currentPassword, storedPassword);
        if (matches) {
            response.put("success", true);
            response.put("message", "비밀번호가 일치합니다.");
            session.setAttribute("passwordVerified", true);
        } else {
            response.put("success", false);
            response.put("message", "비밀번호가 일치하지 않습니다.");
        }

        return response;
    }


    // 비밀번호 변경 처리 (AJAX)
    @PostMapping("/changePassword")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam String newPassword,
                                              @RequestParam String confirmPassword,
                                              @RequestParam String currentPassword) {
        Map<String, Object> response = new HashMap<>();

        if (!newPassword.equals(confirmPassword)) {
            response.put("success", false);
            response.put("message", "비밀번호가 일치하지 않습니다.");
            return response;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long userId = customUser.getUserId();

        // 현재 비밀번호 확인
        String storedPassword = mypageService.getPassword(userId);
        if (storedPassword == null) {
            response.put("success", false);
            response.put("message", "사용자 정보를 찾을 수 없습니다.");
            return response;
        }

        // 현재 비밀번호 검증
        if (!passwordEncoder.matches(currentPassword, storedPassword)) {
            response.put("success", false);
            response.put("message", "현재 비밀번호가 일치하지 않습니다.");
            return response;
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);

        // 비밀번호 업데이트
        boolean isPasswordUpdated = mypageService.updatePassword(userId, encodedPassword);
        if (isPasswordUpdated) {
            response.put("success", true);
            response.put("message", "비밀번호가 성공적으로 변경되었습니다.");
        } else {
            response.put("success", false);
            response.put("message", "비밀번호 변경에 실패했습니다.");
        }
        return response;
    }
}