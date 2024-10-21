package com.example.lookkit.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    public List<CartVO> getCartItems(int userId) {
        return cartMapper.getCartItems(userId);
    }

    public void addItemToCart(CartVO cartVO) {
        cartMapper.addItemToCart(cartVO);
    }

    public void updateCartItem(CartVO cartVO) {
        cartMapper.updateCartItem(cartVO);
    }

    public void deleteCartItem(int cartId) {
        cartMapper.deleteCartItem(cartId);
    }

    public List<CartVO> getSelectedCartItems(List<Integer> cartIds) {
        return cartMapper.getSelectedCartItems(cartIds);
    }

    public double calculateTotalAmount(int userId) {
        List<CartVO> cartItems = cartMapper.getCartItems(userId);
        return cartItems.stream()
                .mapToDouble(item -> item.getProductPrice() * item.getQuantity())
                .sum();
    }
    
}
