package com.example.lookkit.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewVO {
    private long reviewId;
    private Long productId;
    private Long codiId;
    private long userId;
    private int rating;
    private String reviewText;
    private LocalDateTime createdAt;
}