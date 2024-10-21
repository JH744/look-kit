package com.example.lookkit.cart;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CartMapper {

    @Select("SELECT c.*, p.product_name, p.product_price, p.product_thumbnail " +
            "FROM cart c " +
            "JOIN products p ON c.product_id = p.product_id " +
            "WHERE c.user_id = #{userId}")
    List<CartVO> getCartItems(int userId);

    @Insert("INSERT INTO cart (user_id, product_id, quantity) VALUES (#{userId}, #{productId}, #{quantity})")
    void addItemToCart(CartVO cartVO);

    @Update("UPDATE cart SET quantity = #{quantity} WHERE cart_id = #{cartId}")
    void updateCartItem(CartVO cartVO);

    @Delete("DELETE FROM cart WHERE cart_id = #{cartId}")
    void deleteCartItem(int cartId);

    @Select("<script>" +
            "SELECT * FROM cart WHERE cart_id IN " +
            "<foreach item='cartId' collection='cartIds' open='(' separator=',' close=')'>" +
            "#{cartId}" +
            "</foreach>" +
            "</script>")
    List<CartVO> getSelectedCartItems(@Param("cartIds") List<Integer> cartIds);
}

