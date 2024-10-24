package com.example.lookkit.common.dto;

import com.example.lookkit.inquiry.InquiryImageVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductsDTO {
    private long orderId;
    private LocalDateTime orderDate;
    private String totalAmount;
    private String orderStatus;
    private long productId;
    private String productName;
    private String productPrice;
    private int quantity;
    private String brandName;
    private String productThumbnail;
    private boolean isPurchaseConfirmed;
}
