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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
