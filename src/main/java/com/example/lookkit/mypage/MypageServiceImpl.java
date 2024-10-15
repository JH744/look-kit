// MypageServiceImpl.java
package com.example.lookkit.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MypageServiceImpl implements MypageService {

    private final MypageMapper mypageMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MypageServiceImpl(MypageMapper mypageMapper) {
        this.mypageMapper = mypageMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public MypageDTO getUserInfo(int userId) {
        return mypageMapper.getUserInfo(userId);
    }

    @Override
    @Transactional
    public boolean updateUserInfo(MypageDTO mypageDTO) {
        String rawPassword = mypageDTO.getPassword();
        if (rawPassword != null && !rawPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            mypageDTO.setPassword(encodedPassword);
        } else {
            // 비밀번호가 변경되지 않은 경우, 기존 비밀번호를 유지하도록 설정
            MypageDTO existingUser = mypageMapper.getUserInfo(mypageDTO.getUserId());
            if (existingUser != null) {
                mypageDTO.setPassword(existingUser.getPassword());
            }
        }
        int result = mypageMapper.updateUserInfo(mypageDTO);
        return result > 0;
    }

    @Override
    public boolean isEmailUnique(String email, int userId) {
        int count = mypageMapper.checkEmail(email, userId);
        return count == 0;
    }
}
