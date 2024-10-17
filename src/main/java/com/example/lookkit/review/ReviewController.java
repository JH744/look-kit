package com.example.lookkit.review;

import com.example.lookkit.review.ReviewService;
import com.example.lookkit.review.ReviewVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/mypage/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String manageReviews(Model model, Principal principal) {
        long userId = getUserIdFromPrincipal(principal);
        List<ReviewVO> writableReviews = reviewService.getWritableReviews(userId);
        List<ReviewVO> writtenReviews = reviewService.getWrittenReviews(userId);
        model.addAttribute("writableReviews", writableReviews);
        model.addAttribute("writtenReviews", writtenReviews);
        return "manageReviews";
    }

    @PostMapping("/submit")
    public String submitReview(@ModelAttribute ReviewVO review, Principal principal) {
        long userId = getUserIdFromPrincipal(principal);
        review.setUserId(userId);
        reviewService.saveReview(review);
        return "redirect:/mypage/reviews";
    }

    private long getUserIdFromPrincipal(Principal principal) {
        
        return 1L;
    }
}