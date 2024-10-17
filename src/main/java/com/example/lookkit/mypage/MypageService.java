package com.example.lookkit.mypage;

public interface MypageService {
    MypageDTO getUserInfo(long userId);
    boolean updateUserInfo(MypageDTO mypageDTO);
    boolean isEmailDuplicate(String email, long userId);
    boolean updatePassword(long userId, String newPassword);
    String getPassword(long userId);
}
