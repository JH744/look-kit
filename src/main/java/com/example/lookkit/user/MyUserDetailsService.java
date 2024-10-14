package com.example.lookkit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserMapper UserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO user = UserMapper.getUserByUuid(username);
//        if(user != null ) {
//            System.out.println(user.getUserUuid()+user.getAddress()+user.getGender());
//        }
        if (user == null) throw new UsernameNotFoundException("해당 아이디를 가진 사용자가 없습니다: " + username);

        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        if(user.getRole().equals("ADMIN")){
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else {
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
     // 유저아이디, 유저비밀번호, 유저권한, 유저PK-ID 를 세션에 저장
     return  new CustomUser(user.getUserUuid(), user.getPassword(), userAuthorities,user.getUserId());
    }
}




