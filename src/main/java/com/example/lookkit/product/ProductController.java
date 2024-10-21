package com.example.lookkit.product;

import com.example.lookkit.review.ReviewService;
import com.example.lookkit.review.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {


    private final ProductService productService;
    private final ReviewService reviewService;

    @Autowired
    public ProductController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }


    @GetMapping("/product/{id}")
    public String productPage(@PathVariable("id") int id, Model model) {
        ProductVO product = productService.getProductById(id);
        List<ReviewVO> reviews = reviewService.getReviewsByProductId(id);

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "product"; 
    }





}
