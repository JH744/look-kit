package com.example.lookkit.user;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USERS (USER_UUID, USER_NAME, PASSWORD, GENDER, BIRTHDATE, EMAIL, PHONE,ADDRESS) " +
            "VALUES (#{userUuid}, #{userName}, #{password}, #{gender}, #{birthDate}, #{email}, #{phone}, #{address})")
    public boolean insertUser(UserVO user);

    @Select("SELECT USER_ID, USER_UUID, PASSWORD, USER_NAME, GENDER, BIRTHDATE, EMAIL, CREATED_AT, PHONE, LAST_UPDATE, ROLE, ADDRESS FROM USERS WHERE USER_UUID = #{userUuid}")
    public UserVO getUserByUuid(String userUuid);


    @Select("SELECT USER_ID, USER_UUID, PASSWORD, USER_NAME, GENDER, BIRTHDATE, EMAIL, CREATED_AT, PHONE, LAST_UPDATE, ROLE, ADDRESS FROM USERS WHERE USER_ID = #{userid}")
    public UserVO getUserById(Long userid);


    @Update("UPDATE USERS SET PASSWORD = #{newPassword} WHERE USER_UUID = #{userUuid}")
    public int UpdatePassword(String userUuid, String newPassword);


    @Select("SELECT USER_UUID FROM USERS WHERE USER_NAME = #{userName} AND EMAIL = #{email}")
    public String findUserUuidByNameAndEmail(String userName, String email);

    @Select("SELECT USER_NAME FROM USERS WHERE USER_ID = #{userId}")
    public String searchUserName(long userId);

}