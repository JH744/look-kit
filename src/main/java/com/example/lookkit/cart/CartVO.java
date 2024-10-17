package com.example.lookkit.cart;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartVO {
    private int cartId;
    private int userId;
    private int productId;
    private int quantity;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // public int getCodiId() {
    //     return codiId;
    // }

    // public void setCodiId(int codiId) {
    //     this.codiId = codiId;
    // }
}