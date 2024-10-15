package com.example.lookkit.mypage;

public interface MypageService {
    MypageDTO getUserInfo(int userId);
    boolean updateUserInfo(MypageDTO mypageDTO);
    boolean isEmailUnique(String email, int userId);
}
