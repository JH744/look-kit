package com.example.lookkit.inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InquiryVO {
    private long inquiryId;
    private long userId;
    private String inquiryTitle;
    private String inquiryContents;
    private LocalDateTime inquiryCreatedAt;
    private String answerState;
}
