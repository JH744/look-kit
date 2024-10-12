package com.example.lookkit.cart;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartVO {
    private long cartId;
    private long userId;
    private Long productId;
    private Long codiId;
}
