package com.example.lookkit.inquiry;

import com.example.lookkit.common.dto.InquiryImagesDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InquiryMapper {
    @Select("select inquiry_id, user_id, inquiry_title, inquiry_contents, inquiry_created_at, answer_state " +
            "from inquiries where user_id=#{userId} " +
            "order by inquiry_created_at desc")
    public List<InquiryVO> getList(long userId);

    @Delete("delete from inquiries where inquiry_id=#{inquiryId}")
    public void deleteInquiry(long inquiryId);


    @Insert("INSERT INTO inquiries (user_id, inquiry_title, inquiry_contents) " +
            "VALUES (#{userId}, #{inquiryTitle}, #{inquiryContents})")
    @Options(useGeneratedKeys = true, keyProperty = "inquiryId")
    public void uploadInquiry(InquiryVO vo);

    @Insert("INSERT INTO inquiries_images (inquiry_id, image_path) " +
            "VALUES (#{inquiryId}, #{imagePath})")
    public void uploadInquiryImage(InquiryImageVO vo);

    //문의-사진
    @Select("SELECT INQUIRY_ID, USER_ID, INQUIRY_TITLE, INQUIRY_CONTENTS, INQUIRY_CREATED_AT, ANSWER_STATE " +
            "FROM INQUIRIES WHERE INQUIRY_ID = #{inquiryId}")
    @Results({
            @Result(property = "inquiryId", column = "INQUIRY_ID"),
            @Result(property = "userId", column = "USER_ID"),
            @Result(property = "inquiryTitle", column = "INQUIRY_TITLE"),
            @Result(property = "inquiryContents", column = "INQUIRY_CONTENTS"),
            @Result(property = "inquiryCreatedAt", column = "INQUIRY_CREATED_AT"),
            @Result(property = "answerState", column = "ANSWER_STATE"),
            @Result(property = "inquiryImages", column = "INQUIRY_ID",
                    many = @Many(select = "getImages"))
    })
    InquiryImagesDTO getInquiryWithImages(long inquiryId);

    @Select("SELECT INQUIRY_IMAGE_ID, INQUIRY_ID, IMAGE_PATH " +
            "FROM INQUIRIES_IMAGES WHERE INQUIRY_ID = #{inquiryId}")
    List<InquiryImageVO> getImages(long inquiryId);

}
