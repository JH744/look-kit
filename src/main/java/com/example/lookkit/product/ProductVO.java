package com.example.lookkit.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductVO {
    private long productId;
    private long categoryId;
    private Long codiId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productStock;
    private String genderTarget;
    private String productThumbnail;
    private LocalDateTime productCreatedAt;
    private LocalDateTime productUpdatedAt;
}
