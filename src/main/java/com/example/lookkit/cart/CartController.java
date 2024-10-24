package com.example.lookkit.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.lookkit.order.OrderDetailDTO;
import com.example.lookkit.order.OrderVO;
import com.example.lookkit.order.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

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
        int userId = 1; // 실제 서비스에서는 사용자 ID를 인증 정보로 가져와야 합니다.
        List<CartVO> items = cartService.getSelectedCartItems(selectedItems);

        List<OrderDetailDTO> orderDetails = items.stream()
                .map(cartItem -> OrderDetailDTO.builder()
                        .productId(cartItem.getProductId())
                        .productName(cartItem.getProductName())
                        .productPrice(cartItem.getProductPrice())
                        .productThumbnail(cartItem.getProductThumbnail())
                        .quantity(cartItem.getQuantity())
                        .build())
                .collect(Collectors.toList());

        OrderVO orderVO = OrderVO.builder()
                .userId(userId)
                .orderAddressee("홍길동") // 예시 값, 실제로는 사용자 정보 사용
                .orderAddress("서울특별시") // 예시 값, 실제로는 사용자 정보 사용
                .orderPhone("010-1234-5678") // 예시 값, 실제로는 사용자 정보 사용
                .build();

        orderService.createOrder(orderVO, orderDetails);
        model.addAttribute("order", orderVO);
        return "order";
    }
}
