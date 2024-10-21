package com.example.lookkit.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductVO {
    private int productId;
    private int categoryId;
    private int codiId;
    private String productName;
    private String brandName;
    private String productDescription;
    private int productPrice;
    private int productStock;
    private String genderTarget;
    private String productThumbnail;
    private LocalDateTime productCreatedAt;
    private LocalDateTime productUpdatedAt;
    private List<ProductImageVO> productImages;
}