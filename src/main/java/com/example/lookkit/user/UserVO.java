package com.example.lookkit.user;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserVO {
    private long userId;
    private String userUuid;
    private String userName;
    private String password;
    private String gender;
    @DateTimeFormat(pattern = "yyMMdd")
    private LocalDate birthDate;
    private String email;
    private Timestamp createdAt;
    private String phone;
    private Timestamp lastUpdate;
    private String role;
    private String address;
}


