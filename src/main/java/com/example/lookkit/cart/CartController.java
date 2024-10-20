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
        int userId = 1; // 사용자 ID는 실제 서비스에서는 인증 정보로 가져와야 합니다
        List<CartVO> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping
    @ResponseBody
    public String addItemToCart(@RequestBody CartVO cartVO) {
    cartService.addItemToCart(cartVO);
    return "Item added to cart successfully.";
}


    @PutMapping("/update")
    @ResponseBody
    public String updateCartItem(@RequestBody CartVO cartVO) {
        try {
            cartService.updateCartItem(cartVO);
           return "Item updated successfully.";
        } catch (Exception e) {
            return "Error updating item in cart.";
        }
    }

    @DeleteMapping("/delete/{cartId}")
    @ResponseBody
    public String deleteCartItem(@PathVariable int cartId) {
        cartService.deleteCartItem(cartId);
        return "Item deleted successfully.";
    }

    @PostMapping("/order")
    public String createOrder(@RequestBody List<Integer> selectedItems, Model model) {
        List<CartVO> items = cartService.getSelectedCartItems(selectedItems);
        model.addAttribute("selectedItems", items);
        return "order";
    }
}
