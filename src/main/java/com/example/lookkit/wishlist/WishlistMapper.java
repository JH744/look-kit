package com.example.lookkit.wishlist;

import com.example.lookkit.common.dto.ProductWishlistDTO;
import com.example.lookkit.common.dto.UserOrderDTO;
import com.example.lookkit.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WishlistMapper {
    @Select("select * from users where user_id = #{userId}")
    public UserVO getUserInfo(int userId);

    @Select("SELECT p.product_id, product_name, product_description, product_price, product_thumbnail, COUNT(p.product_id) AS like_count\n" +
            "FROM products p\n" +
            "JOIN wishlist w ON p.product_id = w.product_id\n" +
            "where w.user_id = #{userId}\n" +
            "GROUP BY p.product_id")
    public List<ProductWishlistDTO> getWishList(int userId);

}
