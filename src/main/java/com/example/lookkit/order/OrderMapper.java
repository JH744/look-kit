package com.example.lookkit.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO orders (user_id, total_amount, order_status, order_comment, order_date, order_addressee, order_address, order_phone) VALUES (#{userId}, #{totalAmount}, #{orderStatus}, #{orderComment}, #{orderDate}, #{orderAddressee}, #{orderAddress}, #{orderPhone})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void createOrder(OrderVO orderVO);
    
    @Select("<script>" +
            "SELECT o.order_id, oi.product_id, p.product_name, p.product_price, p.product_thumbnail, oi.quantity " +
            "FROM orders o " +
            "JOIN order_items oi ON o.order_id = oi.order_id " +
            "JOIN products p ON oi.product_id = p.product_id " +
            "WHERE o.order_id IN " +
            "<foreach item='item' index='index' collection='orderIds' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<OrderDetailDTO> getOrderDetails(@Param("orderIds") List<Integer> orderIds);

    @Insert("INSERT INTO order_items (order_id, product_id, codi_id, user_id, quantity) VALUES (#{orderId}, #{productId}, #{codiId}, #{userId}, #{quantity})")
    void createOrderDetail(OrderDetailVO orderDetailVO);

    @Delete("DELETE FROM orders WHERE order_id = #{orderId}")
    void deleteOrder(int orderId);

    @Delete("DELETE FROM order_items WHERE order_id = #{orderId}")
    void deleteOrderItems(int orderId);
}
