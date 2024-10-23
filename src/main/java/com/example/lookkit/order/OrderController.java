package com.example.lookkit.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import com.example.lookkit.cart.CartVO;
import com.example.lookkit.cart.CartService;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String orderPage(@RequestParam(required = false) List<Integer> selectedItems, Model model) {
        if (selectedItems != null && !selectedItems.isEmpty()) {
            List<OrderDetailDTO> items = orderService.getOrderDetails(selectedItems);
            int totalAmount = items.stream().mapToInt(item -> item.getProductPrice() * item.getQuantity()).sum();

            model.addAttribute("orderDetails", items);
            model.addAttribute("totalAmount", totalAmount);
        }

        return "order";
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody List<Integer> selectedItems, Model model) {
        int userId = 1; // 실제 서비스에서는 사용자 ID를 인증 정보로 가져와야 합니다.
        List<CartVO> items = cartService.getSelectedCartItems(selectedItems);

        // 주문 상세 정보를 생성합니다.
        List<OrderDetailDTO> orderDetails = items.stream()
                .map(cartItem -> OrderDetailDTO.builder()
                        .productId(cartItem.getProductId())
                        .productName(cartItem.getProductName())
                        .productPrice(cartItem.getProductPrice())
                        .productThumbnail(cartItem.getProductThumbnail())
                        .quantity(cartItem.getQuantity())
                        .build())
                .collect(Collectors.toList());

        // 주문 객체 생성
        OrderVO orderVO = OrderVO.builder()
                .userId(userId)
                .orderAddressee("홍길동") // 예시 값, 실제로는 사용자 정보 사용
                .orderAddress("서울특별시") // 예시 값, 실제로는 사용자 정보 사용
                .orderPhone("010-1234-5678") // 예시 값, 실제로는 사용자 정보 사용
                .build();

        try {
            orderService.createOrder(orderVO, orderDetails);
            model.addAttribute("order", orderVO);
            model.addAttribute("orderDetails", orderDetails); // 주문 상세 정보도 추가
            return "order";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error placing order. Please try again.");
            return "order";
        }
    }

    @PostMapping
    @ResponseBody
    public String createOrder(@RequestBody List<Integer> selectedItems) {
        // 주문 생성 로직은 생략되었으며, 위와 비슷하게 OrderDetailDTO를 사용해 데이터를 생성하도록 수정하면 됩니다.
        return "Order successfully created";
    }

    @PostMapping("/payment")
    @ResponseBody
    public String processPayment(@RequestParam int orderId, @RequestParam String paymentMethod) {
        boolean paymentResult = orderService.processPayment(orderId, paymentMethod);
        return paymentResult ? "Payment successful" : "Payment failed";
    }



    @GetMapping("/addAddress")
    public String addAddressPage() {
        return "addAddress";
    }


    @GetMapping("/orderComplete")
    public String orderCompletePage(Model model) {
       model.addAttribute("message", "주문이 완료되었습니다.");
       return "orderComplete";
    }
}