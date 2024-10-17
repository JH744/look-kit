package com.example.lookkit.inquiry;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class InquiryImageVO {
    private long inquiryImageId;
    private long inquiryId;
    private String imagePath;
}
