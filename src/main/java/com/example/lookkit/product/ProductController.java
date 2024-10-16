package com.example.lookkit.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String productPage(@PathVariable("id") int id, Model model) {
        ProductVO product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product";
    }
}
