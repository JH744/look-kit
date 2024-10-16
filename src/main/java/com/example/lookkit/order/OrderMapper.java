package com.example.lookkit.order;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO orders (user_id, total_amount, order_status, order_comment, order_date, order_addressee, order_address, order_phone) VALUES (#{userId}, #{totalAmount}, #{orderStatus}, #{orderComment}, #{orderDate}, #{orderAddressee}, #{orderAddress}, #{orderPhone})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void createOrder(OrderVO orderVO);

    @Select("SELECT * FROM orders WHERE user_id = #{userId}")
    List<OrderVO> getOrdersByUser(long userId);

    @Select("SELECT * FROM order_items WHERE order_id = #{orderId}")
    List<OrderDetailVO> getOrderDetails(long orderId);

    @Insert("INSERT INTO order_items (order_id, product_id, codi_id, user_id, quantity) VALUES (#{orderId}, #{productId}, #{codiId}, #{userId}, #{quantity})")
    @Options(useGeneratedKeys = true, keyProperty = "orderItemId")
    void createOrderDetail(OrderDetailVO orderDetailVO);

    @Delete("DELETE FROM orders WHERE order_id = #{orderId}")
    void deleteOrder(long orderId);

    @Delete("DELETE FROM order_items WHERE order_id = #{orderId}")
    void deleteOrderItems(long orderId); 
}


