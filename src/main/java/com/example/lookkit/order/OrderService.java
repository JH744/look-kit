package com.example.lookkit.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lookkit.product.ProductVO;
import com.example.lookkit.product.ProductMapper;
import com.example.lookkit.cart.CartService;
import com.example.lookkit.cart.CartVO;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final CartService cartService;

    public OrderService(OrderMapper orderMapper, ProductMapper productMapper, CartService cartService) {
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
        this.cartService = cartService;
    }

    public void createOrder(OrderVO orderVO, List<OrderDetailDTO> orderDetails) {
        // 주문 정보 저장
        orderMapper.createOrder(orderVO);
    
        // 주문 상세 정보 저장
        for (OrderDetailDTO detail : orderDetails) {
            detail.setOrderId(orderVO.getOrderId()); // 주문 ID 설정
            orderMapper.createOrderDetail(detail); // 주문 상세 정보 생성
        }
    }
    
    public void createOrder(OrderVO orderVO, List<Integer> productIds, int quantity) {
        // 주문 생성
        orderMapper.createOrder(orderVO);

        // 주문 상세 생성
        for (Integer productId : productIds) {
            ProductVO product = productMapper.getProductById(productId);
            if (product == null) {
                throw new RuntimeException("Invalid productId: " + productId);
            }
            OrderDetailDTO orderDetail = OrderDetailDTO.builder()
                    .orderId(orderVO.getOrderId())
                    .productId(productId)
                    .quantity(quantity)
                    .productPrice(product.getProductPrice())
                    .productName(product.getProductName())
                    .productThumbnail(product.getProductThumbnail())
                    .build();
            orderMapper.createOrderDetail(orderDetail);
        }
    }

    public List<OrderDetailDTO> getOrderDetails(List<Integer> orderIds) {
        return orderMapper.getOrderDetails(orderIds);
    }

    public boolean processPayment(int orderId, String paymentMethod) {
        // 결제 처리 로직 (현재는 성공 처리)
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

    public void validateProductId(int productId) {
        ProductVO product = productMapper.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Invalid productId: " + productId);
        }
    }

    public List<CartVO> getSelectedCartItems(List<Integer> selectedItems) {
        return cartService.getSelectedCartItems(selectedItems);
    }
}
