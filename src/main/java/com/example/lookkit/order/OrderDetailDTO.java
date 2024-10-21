package com.example.lookkit.order;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private int orderId;
    private int productId;
    private String productName;
    private int productPrice;
    private String productThumbnail;
    private int quantity;
}
