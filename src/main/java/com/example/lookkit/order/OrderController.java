package com.example.lookkit.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String orderPage(@RequestParam List<Integer> selectedItems, Model model) {
        // selectedItems로 주문할 아이템 목록을 가져옵니다.
        List<OrderDetailVO> items = orderService.getOrderDetails(selectedItems);

        OrderVO orderVO = OrderVO.builder()
                .userId(1) // 예시로 사용자 ID 지정, 실제 서비스에서는 세션 등에서 가져와야 함
                .orderDetails(items)
                .build();

        model.addAttribute("order", orderVO);
        return "order";
    }

    @PostMapping
    @ResponseBody
    public String createOrder(@RequestBody List<Integer> selectedItems) {
        try {
            OrderVO orderVO = OrderVO.builder()
                    .userId(1) // 실제 서비스에서는 사용자 ID를 세션에서 가져와야 함
                    .build();
            orderService.createOrder(orderVO, selectedItems);
            return "Order successfully created";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating order";
        }
    }

    @PostMapping("/payment")
    @ResponseBody
    public String processPayment(@RequestParam int orderId, @RequestParam String paymentMethod) {
        boolean paymentResult = orderService.processPayment(orderId, paymentMethod);
        return paymentResult ? "Payment successful" : "Payment failed";
    }

    @PostMapping("/payment/complete")
    public String completePayment(@RequestParam("imp_uid") String impUid,
                                  @RequestParam("merchant_uid") String merchantUid,
                                  @RequestParam("amount") int amount, Model model) {
        boolean result = orderService.processPayment(impUid, merchantUid, amount);

        if (result) {
            model.addAttribute("message", "결제가 완료되었습니다.");
            model.addAttribute("orderId", merchantUid);
            model.addAttribute("amount", amount);
            return "orderComplete";
        } else {
            model.addAttribute("paymentFailed", true);
            return "order";
        }
    }

    @GetMapping("/addAddress")
    public String addAddressPage() {
        return "addAddress";
    }
}
