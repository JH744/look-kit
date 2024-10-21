package com.example.lookkit.product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE product_id = #{productId}")
    ProductVO getProductById(int productId);

    @Select("SELECT product_stock FROM products WHERE product_id = #{productId}")
    int getProductStock(int productId);

    @Update("UPDATE products SET product_stock = product_stock - #{quantity} WHERE product_id = #{productId} AND product_stock >= #{quantity}")
    int updateProductStock(int productId, int quantity);
}