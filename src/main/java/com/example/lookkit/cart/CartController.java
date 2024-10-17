package com.example.lookkit.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String cartPage(Model model) {
        // 테스트용 데이터로 장바구니 아이템을 추가해서 전달할 수 있습니다.
        // 예를 들어, 데이터베이스에서 특정 사용자 ID로 장바구니를 불러와야 합니다.
        int userId = 1; // 예시 userId
        List<CartVO> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "cart";  // `cart.html` 템플릿 반환
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public List<CartVO> getCartItems(@PathVariable int userId) {
        return cartService.getCartItems(userId);
    }

    @PostMapping("/add")
    @ResponseBody
    public String addItemToCart(@RequestBody CartVO cartVO) {
        cartService.addItemToCart(cartVO);
        return "Item added to cart successfully.";
    }

    @PutMapping("/update")
    @ResponseBody
    public String updateCartItem(@RequestBody CartVO cartVO) {
        cartService.updateCartItem(cartVO);
        return "Item updated successfully.";
    }

    @DeleteMapping("/delete/{cartId}")
    @ResponseBody
    public String deleteCartItem(@PathVariable int cartId) {
        cartService.deleteCartItem(cartId);
        return "Item deleted successfully.";
    }
}
