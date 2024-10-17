package com.example.lookkit.order;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderVO {
    private long orderId;
    private long userId;
    private int totalAmount;
    private String orderStatus;
    private String orderComment;
    private LocalDateTime orderDate;
    private String orderAddressee;
    private String orderAddress;
    private String orderPhone;
    private List<OrderDetailVO> orderDetails; // 주문 상세 정보 필드 추가

    public List<OrderDetailVO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailVO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
