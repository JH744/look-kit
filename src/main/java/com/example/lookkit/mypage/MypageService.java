package com.example.lookkit.mypage;

import com.example.lookkit.user.UserVO;

public interface MypageService {
    UserVO getUserInfo(int userId);
    boolean updateUserInfo(MypageDTO mypageDTO);
    boolean isEmailUnique(String email, int userId);
}
