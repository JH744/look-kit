package com.example.lookkit.review;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public void saveReview(ReviewVO review) {
        reviewMapper.insertReview(review);
    }

    public List<ReviewVO> getWritableReviews(long userId) {
        return reviewMapper.getWritableReviews(userId);
    }

    public List<ReviewVO> getWrittenReviews(long userId) {
        return reviewMapper.getWrittenReviews(userId);
    }

    public List<ReviewVO> getReviewsByProductId(int id) {
        return reviewMapper.findReviewsByProductId(id);
    }
}