package com.example.lookkit.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserVO {
    private long userId;       // ID 필드 타입을 long으로 변경
    private String userUuid;
    private String userName;
    private String password;
    private String gender;
    private Date birthDate;
    private String email;
    private LocalDateTime createdAt;
    private String phone;
    private LocalDateTime lastUpdate;
    private String role;
    private String address;
}