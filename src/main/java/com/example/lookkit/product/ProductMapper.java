package com.example.lookkit.product;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE product_id = #{id}")
    ProductVO getProductById(long productId);

    @Select("SELECT * FROM products_images WHERE product_id = #{productId}")
    List<ProductImageVO> getProductImagesByProductId(long productId);

}