package com.example.lookkit.orderManagement;

import com.example.lookkit.common.dto.OrderProductsDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage/manage")
public class OrderManagementController {
    @Autowired
    OrderManagementService service;

    @GetMapping
    public ModelAndView getMyOrder(HttpSession session) {
        long userId = (long) session.getAttribute("userid");
        List<OrderProductsDTO> list =  service.getProductsByOrder(userId);
        Map<String, Long> statusCounts = service.getOrderStatusCounts(userId);

        ModelAndView mv = new ModelAndView("mypage/orderManagement");
        mv.addObject("products", list);
        mv.addObject("countPending", statusCounts.getOrDefault("pending", 0L));
        mv.addObject("countShipped", statusCounts.getOrDefault("shipped", 0L));
        mv.addObject("countDelivered", statusCounts.getOrDefault("delivered", 0L));
        mv.addObject("countCompleted", statusCounts.getOrDefault("completed", 0L));

        for(OrderProductsDTO o:list){
            System.out.println(">>>>>>>>" + o);
        }
        return mv;
    }
}
