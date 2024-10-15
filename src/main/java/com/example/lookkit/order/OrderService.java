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

    public boolean processPayment(int orderId, String paymentMethod) {
        // 결제 처리 로직 추가 (예: 외부 결제 API 호출 등)
        // 현재는 간단히 성공 여부를 반환하도록 구현
        return true;
    }
    public boolean processPayment(String impUid, String merchantUid, int amount) {
        // 결제 정보 처리 로직 추가 (DB에 저장 등)
        try {
            // 예시: 데이터베이스에 결제 정보 저장 또는 검증 로직
            System.out.println("결제 정보 처리: impUid=" + impUid + ", merchantUid=" + merchantUid + ", amount=" + amount);
            return true;  // 결제 성공 시 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // 결제 실패 시 false 반환
        }
    }
    
}