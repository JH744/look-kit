package com.example.lookkit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    // 유저 추가
    public boolean insertUser(UserVO user){
        try {
            // 해싱된 비밀번호로 재설정
            user.setPassword(encoder.encode(user.getPassword()));
        }catch (Exception e){
            System.out.println("에러발생: "+e.getMessage());
        }
        return userMapper.insertUser(user);
    }


    // 아이디로 유저 조회
    public UserVO getUserByUuid(String userId) {
        return userMapper.getUserByUuid(userId);
    }


    public UserVO getUserById(long userId) {
        return userMapper.getUserById(userId);
    }
}
