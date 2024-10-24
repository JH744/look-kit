package com.example.lookkit.common.dto;

import lombok.Getter;

@Getter
public class ProductWishlistDTO {
    private long productId;
    private String productName;
    private String brandName;
    private double productPrice;
    private String productThumbnail;
    private int likeCount;
}
