package com.example.lookkit.wishlist;

import com.example.lookkit.common.dto.ProductWishlistDTO;
import com.example.lookkit.user.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WishlistMapper {
    @Select("select * from users where user_id = #{userId}")
    public UserVO getUserInfo(long userId);

    @Select("SELECT p.product_id, product_name, product_description, product_price, product_thumbnail, COUNT(p.product_id) AS like_count\n" +
            "FROM products p\n" +
            "JOIN wishlist w ON p.product_id = w.product_id\n" +
            "WHERE w.user_id = #{userId}\n" +
            "GROUP BY p.product_id")
    public List<ProductWishlistDTO> getWishList(long userId);

    // 찜하기
    @Insert("INSERT INTO wishlist (user_id, product_id, codi_id) " +
            "VALUES (#{userId}, #{productId}, #{codiId})")
    @Options(useGeneratedKeys = true, keyProperty = "wishlistId")
    public int addWishlistItem(WishlistVO wishlistVO);

    //찜삭제
    @Insert("DELETE FROM wishlist WHERE user_id = #{userId} AND product_id = #{productId}")
    public int removeWishlistItem(WishlistVO wishlistVO);

    // 상품이 해당유저의 찜목록에 있는지
    @Select("SELECT user_id, product_id, codi_id FROM wishlist WHERE user_id = #{userId} AND product_id = #{productId}")
    ProductWishlistDTO getWishlistItem(WishlistVO wishlistVO);

}
