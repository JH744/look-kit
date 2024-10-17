package com.example.lookkit.common.dto;

import com.example.lookkit.inquiry.InquiryImageVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InquiryImagesDTO {
    private Long inquiryId;
    private Long userId;
    private String inquiryTitle;
    private String inquiryContents;
    private LocalDateTime inquiryCreatedAt;
    private String answerState;
    private List<InquiryImageVO> inquiryImages = new ArrayList<>();  // 기본값으로 빈 리스트 설정
}
