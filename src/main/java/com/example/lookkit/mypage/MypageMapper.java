package com.example.lookkit.mypage;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.example.lookkit.mypage.MypageDTO;

@Mapper
public interface MypageMapper {

    // 사용자 정보 조회 (비밀번호 제외)
    @Select("SELECT USER_ID AS userId, " +
            "USER_UUID AS userUuid, " +
            "USER_NAME AS name, " +
            "PHONE AS phoneNumber, " +
            "BIRTHDATE AS birthDate, " +
            "EMAIL AS email, " +
            "ADDRESS AS address " +
            "FROM users " +
            "WHERE USER_ID = #{userId}")
    MypageDTO getUserInfo(@Param("userId") long userId);

    // 사용자 정보 업데이트 (비밀번호 제외)
    @Update("UPDATE users SET " +
            "USER_NAME = #{name}, " +
            "PHONE = #{phoneNumber}, " +
            "BIRTHDATE = #{birthDate}, " +
            "EMAIL = #{email}, " +
            "ADDRESS = #{address}, " +
            "LAST_UPDATE = NOW() " +
            "WHERE USER_ID = #{userId}")
    int updateUserInfo(MypageDTO mypageDTO);

    // 비밀번호 업데이트
    @Update("UPDATE users SET " +
            "PASSWORD = #{password}, " +
            "LAST_UPDATE = NOW() " +
            "WHERE USER_ID = #{userId}")
    int updatePassword(@Param("password") String password, @Param("userId") long userId);

    // 이메일 중복 확인
    @Select("SELECT COUNT(*) FROM users WHERE EMAIL = #{email} AND USER_ID != #{userId}")
    int checkEmail(@Param("email") String email, @Param("userId") long userId);

    // 현재 비밀번호 가져오기
    @Select("SELECT PASSWORD FROM users WHERE USER_ID = #{userId}")
    String getPassword(@Param("userId") long userId);
}
