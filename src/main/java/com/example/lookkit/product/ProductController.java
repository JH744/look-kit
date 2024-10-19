package com.example.lookkit.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.lookkit.review.ReviewService;
import com.example.lookkit.review.ReviewVO;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;

    @Autowired
    public ProductController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public String productPage(@PathVariable("id") int id, Model model) {
        ProductVO product = productService.getProductById(id);
        List<ReviewVO> reviews = reviewService.getReviewsByProductId(id);

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "product"; 
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public String addToCart(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
        // 장바구니에 추가하는 로직 (CartService 사용)
        return "redirect:/cart";
    }
}
