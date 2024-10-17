package com.example.lookkit;

import com.example.lookkit.common.dto.InquiryImagesDTO;
import com.example.lookkit.inquiry.InquiryImageVO;
import com.example.lookkit.inquiry.InquiryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class InquiryMapperTest {

    @Autowired
    private InquiryMapper inquiryMapper;

    @Test
    public void testGetInquiryWithImages() {
        long inquiryId = 1L;  // 테스트용 ID

        InquiryImagesDTO inquiry = inquiryMapper.getInquiryWithImages(inquiryId);

        assertNotNull(inquiry); // 결과가 null이 아닌지 확인
        assertEquals(inquiryId, inquiry.getInquiryId()); // inquiryId가 올바르게 매핑되는지 확인
        assertNotNull(inquiry.getInquiryImages()); // 자식 리스트가 null이 아닌지 확인
        assertTrue(inquiry.getInquiryImages().size() > 0); // 자식 리스트가 존재하는지 확인
    }

}
