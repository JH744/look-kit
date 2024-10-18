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
    private double totalAmount;
    private String orderStatus;
    private long productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private String productDescription;
    private String productThumbnail;
}
