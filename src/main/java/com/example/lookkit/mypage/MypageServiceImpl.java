package com.example.lookkit.mypage;//package com.example.lookkit.mypage;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class MypageServiceImpl implements MypageService {
//
//    private final MypageMapper mypageMapper;
//
//    @Override
//    public MypageDTO getUserInfo(long userId) {
//        return mypageMapper.getUserInfo(userId);
//    }
//
//    @Override
//    @Transactional
//    public boolean updateUserInfo(MypageDTO mypageDTO) {
//        int rows = mypageMapper.updateUserInfo(mypageDTO);
//        return rows > 0;
//    }
//
//    @Override
//    public boolean isEmailDuplicate(String email, long userId) {
//        int count = mypageMapper.checkEmail(email, userId);
//        return count > 0;
//    }
//
//    @Override
//    @Transactional
//    public boolean updatePassword(long userId, String newPassword) {
//        int rows = mypageMapper.updatePassword(newPassword, userId);
//        return rows > 0;
//    }
//
//    @Override
//    public String getPassword(long userId) {
//        return mypageMapper.getPassword(userId);
//    }
//}
