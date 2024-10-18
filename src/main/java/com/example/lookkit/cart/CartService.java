package com.example.lookkit.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.security.Principal;

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

    // private int getUserIdFromPrincipal(Principal principal) {
    //     // Principal을 통해 사용자 ID를 가져오는 로직 구현
    //     return 1; // 예시로 사용자 ID 1 반환 (실제 구현 필요)
    // }
}
