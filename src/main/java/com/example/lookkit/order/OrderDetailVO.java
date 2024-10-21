package com.example.lookkit.order;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailVO {
    private int orderItemId;
    private int orderId;
    private int productId;
    private int codiId;
    private int userId;
    private int quantity;
    private int productPrice;
    private String productName; 
    private String productThumbnail; 

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
