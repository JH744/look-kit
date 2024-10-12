package com.example.lookkit.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}
