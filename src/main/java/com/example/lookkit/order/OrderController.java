package com.example.lookkit.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String orderPage(Model model) {
        return "order"; 
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
        // 결제 완료 정보를 주문DB에 넣어야할까
        boolean result = orderService.processPayment(impUid, merchantUid, amount);
        
        if (result) {
            // 결제 완료 정보를 모델에 추가하여 `orderComplete` 페이지로 전달
            model.addAttribute("message", "결제가 완료되었습니다.");
            model.addAttribute("orderId", merchantUid);
            model.addAttribute("amount", amount);
            return "orderComplete"; 
        } else {
            // 결제 실패 시에도 `order.html` 페이지를 렌더링하도록 설정
            model.addAttribute("paymentFailed", true);
            return "order"; 
        }
    }

    @GetMapping("/addAddress")
    public String addAddressPage() {
        return "addAddress"; 
    }
    
}