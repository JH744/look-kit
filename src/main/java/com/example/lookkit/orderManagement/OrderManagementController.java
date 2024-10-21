package com.example.lookkit.orderManagement;

import com.example.lookkit.common.dto.OrderProductsDTO;
import com.example.lookkit.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage/manage")
public class OrderManagementController {
    @Autowired
    OrderManagementService service;
    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView getMyOrder(HttpSession session) {
        long userId = (long) session.getAttribute("userid");
        List<OrderProductsDTO> list =  service.getProductsByOrder(userId);
        Map<String, Long> statusCounts = service.getOrderStatusCounts(userId);

        ModelAndView mv = new ModelAndView("mypage/orderManagement");
        mv.addObject("username", userService.searchUserName(userId));
        mv.addObject("products", list);
        mv.addObject("countPending", statusCounts.getOrDefault("pending", 0L));
        mv.addObject("countShipped", statusCounts.getOrDefault("shipped", 0L));
        mv.addObject("countDelivered", statusCounts.getOrDefault("delivered", 0L));
        mv.addObject("countCompleted", statusCounts.getOrDefault("completed", 0L));

        return mv;
    }

    @PostMapping("/update/confirmed")
    public ResponseEntity<?> updatePurchaseConfirmed(@RequestBody Map<String, Long> data) {
        Long orderId = data.get("orderId");
        Long productId = data.get("productId");

        boolean success = service.updatePurchaseConfirmed(orderId, productId);

        if (success) {
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("success", false));
        }
    }
}
