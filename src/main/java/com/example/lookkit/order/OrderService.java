package com.example.lookkit.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lookkit.cart.CartService;
import com.example.lookkit.cart.CartVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartService cartService;

    public void createOrder(OrderVO orderVO, List<Integer> selectedCartIds) {
        orderMapper.createOrder(orderVO);

        // List<CartVO> selectedCartItems = cartService.getSelectedCartItems(selectedCartIds);
        // List<OrderDetailVO> orderDetails = selectedCartItems.stream().map(cartItem -> OrderDetailVO.builder()
        //             .orderId(orderVO.getOrderId())
        //             .productId(cartItem.getProductId())
        //             .userId(cartItem.getUserId())
        //             .quantity(cartItem.getQuantity())
        //             .build()
        // ).collect(Collectors.toList());

        // for (OrderDetailVO orderDetail : orderDetails) {
    //     //     orderMapper.createOrderDetail(orderDetail);
    //     // }

    //     selectedCartItems.forEach(cartItem -> cartService.deleteCartItem(cartItem.getCartId()));
    }

    public List<OrderVO> getOrdersByUser(int userId) {
        return orderMapper.getOrdersByUser(userId);
    }

    public List<OrderDetailVO> getOrderDetails(List<Integer> orderIds) {
        return orderMapper.getOrderDetails(orderIds);
    }

    public void deleteOrder(int orderId) {
        orderMapper.deleteOrderItems(orderId);
        orderMapper.deleteOrder(orderId);
    }

    public boolean processPayment(int orderId, String paymentMethod) {
        return true;
    }

    public boolean processPayment(String impUid, String merchantUid, int amount) {
        try {
            System.out.println("결제 정보 처리: impUid=" + impUid + ", merchantUid=" + merchantUid + ", amount=" + amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
