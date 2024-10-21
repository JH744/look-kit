package com.example.lookkit.product;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductImageVO {
    private int productImageId;
    private int productId;
    private String imagePath;
}
