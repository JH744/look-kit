package com.example.lookkit.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lookkit.product.ProductVO;
import com.example.lookkit.cart.CartMapper;
import com.example.lookkit.product.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    private ProductMapper productMapper;
    private CartMapper cartMapper;


    public OrderService(OrderMapper orderMapper, ProductMapper productMapper, CartMapper cartMapper) {
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
        this.cartMapper = cartMapper;
    }

    public void createOrder(OrderVO orderVO, List<OrderDetailVO> orderDetails) {
        // 주문 생성
        orderMapper.createOrder(orderVO);

        // 주문 상세 생성
        for (OrderDetailVO detail : orderDetails) {
            ProductVO product = productMapper.getProductById(detail.getProductId());
            if (product == null) {
                throw new RuntimeException("Invalid productId: " + detail.getProductId());
            }
            detail.setOrderId(orderVO.getOrderId());
            detail.setProductName(product.getProductName());
            detail.setProductThumbnail(product.getProductThumbnail());
            orderMapper.createOrderDetail(detail);
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
            OrderDetailVO orderDetail = OrderDetailVO.builder()
                    .orderId(orderVO.getOrderId())
                    .productId(productId)
                    .userId(orderVO.getUserId())
                    .quantity(quantity)
                    .productPrice(product.getProductPrice())
                    .productName(product.getProductName())
                    .productThumbnail(product.getProductThumbnail())
                    .build();
            orderMapper.createOrderDetail(orderDetail);
        }
    }

    public List<OrderDetailVO> getOrderDetails(List<Integer> selectedItems) {
        // 장바구니에서 선택된 상품 정보를 가져와서 주문 상세 목록으로 변환
        return cartMapper.getSelectedCartItems(selectedItems).stream()
                .map(cartItem -> OrderDetailVO.builder()
                        .productId(cartItem.getProductId())
                        .codiId(cartItem.getCodiId())
                        .userId(cartItem.getUserId())
                        .quantity(cartItem.getQuantity())
                        .productPrice(cartItem.getProductPrice())
                        .productName(cartItem.getProductName())
                        .productThumbnail(cartItem.getProductThumbnail())
                        .build())
                .collect(Collectors.toList());
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
}
