package com.example.lookkit.user;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean insertUser(UserVO user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 해싱된 비밀번호로 재설정
        try {
            user.setPassword(encoder.encode(user.getPassword()));

        }catch (Exception e){
            System.out.println("에러발생: "+e.getMessage());
        }
        return userMapper.insertUser(user);
    }
}
