package com.example.lookkit.order;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailVO {
    private long orderItemId;
    private long orderId;
    private Long productId;
    private Long codiId;
    private long userId;
    private int quantity;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
