package com.example.lookkit.product;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductImageVO {
    private long productImageId;
    private long productId;
    private String imagePath;
}
