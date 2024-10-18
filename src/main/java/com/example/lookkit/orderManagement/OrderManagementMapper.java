package com.example.lookkit.orderManagement;

import com.example.lookkit.common.dto.OrderProductsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderManagementMapper {

    @Select("SELECT o.ORDER_ID, o.ORDER_DATE, o.ORDER_STATUS, oi.PRODUCT_ID, o.TOTAL_AMOUNT, " +
            "p.product_description, p.PRODUCT_NAME, p.PRODUCT_PRICE, oi.QUANTITY, p.PRODUCT_THUMBNAIL " +
            "FROM ORDERS o " +
            "JOIN ORDER_ITEMS oi ON o.ORDER_ID = oi.ORDER_ID " +
            "JOIN PRODUCTS p ON oi.PRODUCT_ID = p.PRODUCT_ID " +
            "WHERE o.USER_ID = #{userId}")
    public List<OrderProductsDTO> getProductsByOrder(long userId);
}
