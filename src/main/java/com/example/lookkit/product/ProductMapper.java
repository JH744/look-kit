package com.example.lookkit.product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE product_id = #{id}")
    ProductVO getProductById(long productId);


    @Select("SELECT p.* " +
            "FROM products p " +
            "JOIN categories c ON p.category_id = c.category_id " +
            "WHERE c.category_type = #{categoryType}")
    List<ProductVO> getProductsByCategoryType(String categoryType);

}
