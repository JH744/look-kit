package com.example.lookkit.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void createOrder(OrderVO orderVO) {
        orderMapper.createOrder(orderVO);
        
        for (OrderDetailVO orderDetail : orderVO.getOrderDetails()) {
            orderDetail.setOrderId(orderVO.getOrderId());
            orderMapper.createOrderDetail(orderDetail);
        }
    }

    public List<OrderVO> getOrdersByUser(long userId) {
        return orderMapper.getOrdersByUser(userId);
    }

    public List<OrderDetailVO> getOrderDetails(long orderId) {
        return orderMapper.getOrderDetails(orderId);
    }

    public void deleteOrder(long orderId) {
        orderMapper.deleteOrderItems(orderId);
        orderMapper.deleteOrder(orderId);
    }
}