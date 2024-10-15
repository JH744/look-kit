package com.example.lookkit.inquiry;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface InquiryMapper {
    @Select("select inquiry_id, user_id, inquiry_title, inquiry_contents, inquiry_created_at, answer_state " +
            "from inquiries where user_id=#{userId}")
    public List<InquiryVO> getList(int userId);

    @Delete("delete from inquiries where inquiry_id=#{inquiryId}")
    public void deleteInquiry(int inquiryId);
}
