package com.example.lookkit.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageMapper dao;

    public MypageDTO getUserInfo(long userId){
        return dao.getUserInfo(userId);
    }

    public boolean updateUserInfo(MypageDTO mypageDTO){
        // 최소 하나 이상의 행이 업데이트되면 true 반환
        return dao.updateUserInfo(mypageDTO) > 0;
    }

    public boolean isEmailDuplicate(String email, long userId){
        // 다른 사용자가 이미 해당 이메일을 사용 중이면 true 반환
        return dao.checkEmail(email, userId) > 0;
    }

    public boolean updatePassword(long userId, String newPassword){
        // 비밀번호가 성공적으로 업데이트되면 true 반환
        return dao.updatePassword(newPassword, userId) > 0;
    }

    public String getPassword(long userId){
        return dao.getPassword(userId);
    }
}
