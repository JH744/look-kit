package com.example.lookkit.product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE product_id = #{productId}")
    ProductVO getProductById(int productId);

    @Select("SELECT product_stock FROM products WHERE product_id = #{productId}")
    int getProductStock(int productId);

    @Update("UPDATE products SET product_stock = product_stock - #{quantity} WHERE product_id = #{productId} AND product_stock >= #{quantity}")
    int updateProductStock(int productId, int quantity);

    @Select("SELECT p.* " +
            "FROM products p " +
            "JOIN categories c ON p.category_id = c.category_id " +
            "WHERE c.category_type = #{categoryType}")
    List<ProductVO> getProductsByCategoryType(String categoryType);

    // 키워드로 상품명 or 설명에서 검색
    @Select("SELECT * FROM products " +
            "WHERE product_name " +
            "LIKE CONCAT('%', #{keyword}, '%') " +
            "OR product_description " +
            "LIKE CONCAT('%', #{keyword}, '%')")
    List<ProductVO> searchProductsByKeyword(String keyword);
}
