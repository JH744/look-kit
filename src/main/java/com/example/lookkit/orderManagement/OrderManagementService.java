package com.example.lookkit.orderManagement;

import com.example.lookkit.common.dto.OrderProductsDTO;
import com.example.lookkit.inquiry.InquiryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderManagementService {
    @Autowired
    OrderManagementMapper dao;

    public List<OrderProductsDTO> getProductsByOrder(long userId){
        return  dao.getProductsByOrder(userId);
    }

    public Map<String, Long> getOrderStatusCounts(Long userId) {
        List<OrderProductsDTO> list = getProductsByOrder(userId);

        return list.stream()
                .collect(Collectors.groupingBy(OrderProductsDTO::getOrderId))
                .values().stream()
                .map(orderProducts -> orderProducts.get(0).getOrderStatus())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public boolean updatePurchaseConfirmed(Long orderId, Long productId) {
        try {
            dao.updatePurchaseConfirmed(orderId, productId, true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
