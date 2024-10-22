package com.example.lookkit.orderManagement;

import com.example.lookkit.common.dto.OrderProductsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderManagementMapper {

    @Select("SELECT o.ORDER_ID, o.ORDER_DATE, o.ORDER_STATUS, oi.PRODUCT_ID, FORMAT(o.TOTAL_AMOUNT, 0) AS TOTAL_AMOUNT, " +
            "p.BRAND_NAME, p.PRODUCT_NAME, FORMAT(p.PRODUCT_PRICE, 0) AS PRODUCT_PRICE, oi.QUANTITY, p.PRODUCT_THUMBNAIL, oi.IS_PURCHASE_CONFIRMED " +
            "FROM ORDERS o " +
            "JOIN ORDER_ITEMS oi ON o.ORDER_ID = oi.ORDER_ID " +
            "JOIN PRODUCTS p ON oi.PRODUCT_ID = p.PRODUCT_ID " +
            "WHERE o.USER_ID = #{userId}")
    public List<OrderProductsDTO> getProductsByOrder(long userId);

    @Update("UPDATE ORDER_ITEMS " +
            "SET is_purchase_confirmed = #{confirmed} " +
            "WHERE order_id = #{orderId} AND product_id = #{productId}")
    void updatePurchaseConfirmed(@Param("orderId") long orderId,
                                 @Param("productId") long productId,
                                 @Param("confirmed") boolean confirmed);

}
