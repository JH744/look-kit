package com.example.lookkit.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USERS (USER_UUID, USER_NAME, PASSWORD, GENDER, BIRTHDATE, EMAIL, PHONE,ADDRESS) " +
            "VALUES (#{userUuid}, #{userName}, #{password}, #{gender}, #{birthDate}, #{email}, #{phone}, #{address})")
    public boolean insertUser(UserVO user);
}