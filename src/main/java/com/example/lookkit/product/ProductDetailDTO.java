package com.example.lookkit.product;

import java.util.List;

public class ProductDetailDTO {
    private ProductVO product;
    private List<ProductImageVO> images;
    private CategoryVO category;
//.private List<ReviewVO> reviews;

    // Getters and setters
    public ProductVO getProduct() {
        return product;
    }

    public void setProduct(ProductVO product) {
        this.product = product;
    }

    public List<ProductImageVO> getImages() {
        return images;
    }

    public void setImages(List<ProductImageVO> images) {
        this.images = images;
    }

    public CategoryVO getCategory() {
        return category;
    }

    public void setCategory(CategoryVO category) {
        this.category = category;
    }

//     public List<ReviewVO> getReviews() {
//         return reviews;
//     }

//     public void setReviews(List<ReviewVO> reviews) {
//         this.reviews = reviews;
//     }
}