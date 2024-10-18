package com.example.lookkit.cart;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CartMapper {

    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    List<CartVO> getCartItems(int userId);

    @Insert("INSERT INTO cart (user_id, product_id) VALUES (#{userId}, #{productId})")
    void addItemToCart(CartVO cartVO);

    @Update("UPDATE cart SET product_id = #{productId} WHERE cart_id = #{cartId}")
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
