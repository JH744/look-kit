package com.example.lookkit.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String orderPage(Model model) {
        // 필요한 데이터를 모델에 추가 (필요시)
        return "order"; // order.html 파일을 반환
    }
}

