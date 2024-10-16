package com.example.lookkit.cart;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CartMapper {

    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    List<CartVO> getCartItems(int userId);

    @Insert("INSERT INTO cart (user_id, product_id, codi_id) VALUES (#{userId}, #{productId}, #{codiId})")
    void addItemToCart(CartVO cartVO);

    @Update("UPDATE cart SET product_id = #{productId}, codi_id = #{codiId} WHERE cart_id = #{cartId}")
    void updateCartItem(CartVO cartVO);

    @Delete("DELETE FROM cart WHERE cart_id = #{cartId}")
    void deleteCartItem(int cartId);
}