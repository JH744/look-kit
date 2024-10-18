package com.example.lookkit.review;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ReviewMapper {
    @Select("SELECT * FROM REVIEWS WHERE USER_ID = #{userId} AND REVIEW_TEXT IS NULL")
    List<ReviewVO> getWritableReviews(long userId);

    @Select("SELECT * FROM REVIEWS WHERE USER_ID = #{userId} AND REVIEW_TEXT IS NOT NULL")
    List<ReviewVO> getWrittenReviews(long userId);

    @Insert("INSERT INTO REVIEWS (PRODUCT_ID, USER_ID, RATING, REVIEW_TEXT, CREATED_AT) VALUES (#{productId}, #{userId}, #{rating}, #{reviewText}, NOW())")
    void insertReview(ReviewVO review);

    @Select("SELECT * FROM REVIEWS WHERE PRODUCT_ID = #{productId}")
    List<ReviewVO> findReviewsByProductId(int id);
}