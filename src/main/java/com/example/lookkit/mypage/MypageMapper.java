// MypageMapper.java
package com.example.lookkit.mypage;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MypageMapper {

    @Select("SELECT USER_ID AS userId, " +
            "USER_UUID AS userUuid, " +
            "PASSWORD AS password, " +
            "USER_NAME AS name, " +
            "PHONE AS phoneNumber, " +
            "BIRTHDATE AS birthDate, " +
            "EMAIL AS email, " +
            "ADDRESS AS address, " +
            "ZIPCODE AS zipcode, " +
            "DETAIL_ADDRESS AS detailAddress " +
            "FROM users " +
            "WHERE USER_ID = #{userId}")
    MypageDTO getUserInfo(@Param("userId") int userId);

    @Update("UPDATE users SET " +
            "PASSWORD = #{password}, " +
            "USER_NAME = #{name}, " +
            "PHONE = #{phoneNumber}, " +
            "BIRTHDATE = #{birthDate}, " +
            "EMAIL = #{email}, " +
            "ADDRESS = #{address}, " +
            "ZIPCODE = #{zipcode}, " +
            "DETAIL_ADDRESS = #{detailAddress}, " +
            "LAST_UPDATE = NOW() " +
            "WHERE USER_ID = #{userId}")
    int updateUserInfo(MypageDTO mypageDTO);

    @Select("SELECT COUNT(*) FROM users WHERE EMAIL = #{email} AND USER_ID != #{userId}")
    int checkEmail(@Param("email") String email, @Param("userId") int userId);
}