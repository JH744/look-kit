package com.example.lookkit.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USERS (USER_UUID, USER_NAME, PASSWORD, GENDER, BIRTHDATE, EMAIL, PHONE,ADDRESS) " +
            "VALUES (#{userUuid}, #{userName}, #{password}, #{gender}, #{birthDate}, #{email}, #{phone}, #{address})")
    public boolean insertUser(UserVO user);

    @Select("SELECT USER_ID, USER_UUID, PASSWORD, USER_NAME, GENDER, BIRTHDATE, EMAIL, CREATED_AT, PHONE, LAST_UPDATE, ROLE, ADDRESS FROM USERS WHERE USER_UUID = #{userUuid}")
    UserVO getUserByUuid(String userUuid);


    @Select("SELECT USER_ID, USER_UUID, PASSWORD, USER_NAME, GENDER, BIRTHDATE, EMAIL, CREATED_AT, PHONE, LAST_UPDATE, ROLE, ADDRESS FROM USERS WHERE USER_ID = #{userid}")
    UserVO getUserById(Long userid);


    @Select("SELECT USER_UUID FROM USER WHERE USER_NAME = #{userName} AND EMAIL = #{email}")
    public String findUserUuidByNameAndEmail(String userName, String email);

}