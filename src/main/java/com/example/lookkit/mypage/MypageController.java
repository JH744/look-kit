package com.example.lookkit.mypage;

import com.example.lookkit.user.CustomUser;
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

    // 회원정보 조회 페이지
    @GetMapping("/userinfo")
    public String userinfo(Model model) {
        // 인증된 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long userId = customUser.getUserId();

        MypageDTO userInfo = mypageService.getUserInfo(userId);
        model.addAttribute("userInfo", userInfo);
        return "mypage/userinfo"; // Thymeleaf 템플릿 이름
    }

    // 회원정보 업데이트 처리
    @PostMapping("/update")
    public String updateUserInfo(@Valid @ModelAttribute("userInfo") MypageDTO mypageDTO, BindingResult bindingResult, Model model) {
        // 인증된 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/auth/login";
        }
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long userId = customUser.getUserId();

        if (bindingResult.hasErrors()) {
            // 검증 오류가 있는 경우, 에러 메시지를 모델에 추가하고 폼 페이지로 돌아갑니다.
            model.addAttribute("message", "입력한 정보에 오류가 있습니다. 다시 확인해주세요.");
            return "mypage/userinfo";
        }

        // 이메일 중복 확인
        if (mypageService.isEmailDuplicate(mypageDTO.getEmail(), userId)) {
            // 이메일 중복인 경우
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

    // 비밀번호 확인 처리 (AJAX)
    @PostMapping("/verifyPassword")
    @ResponseBody
    public Map<String, Object> verifyPassword(@RequestParam String currentPassword) {
        Map<String, Object> response = new HashMap<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long userId = customUser.getUserId();

        // 현재 비밀번호 가져오기
        String storedPassword = mypageService.getPassword(userId);
        if (storedPassword == null) {
            response.put("success", false);
            response.put("message", "사용자 정보를 찾을 수 없습니다.");
            return response;
        }

        // 비밀번호 비교
        boolean matches = passwordEncoder.matches(currentPassword, storedPassword);
        if (matches) {
            response.put("success", true);
            response.put("message", "비밀번호가 일치합니다.");
        } else {
            response.put("success", false);
            response.put("message", "비밀번호가 일치하지 않습니다.");
        }

        return response;
    }

    // 비밀번호 변경 처리 (AJAX)
    @PostMapping("/changePassword")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam String newPassword, @RequestParam String confirmPassword) {
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
