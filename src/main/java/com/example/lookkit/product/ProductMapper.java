package com.example.lookkit.product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE product_id = #{id}")
    ProductVO getProductById(long productId);
}
