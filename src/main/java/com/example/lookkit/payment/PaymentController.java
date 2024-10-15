package com.example.lookkit.payment;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping("/test")
    public String paymentTestPage() {
        return "test"; // test.html 페이지로 이동
    }
    
}
