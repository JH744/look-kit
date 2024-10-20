package com.example.lookkit.coordiset;

import com.example.lookkit.product.ProductVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CoordisetMapper {

    @Select("SELECT * FROM codi ORDER BY codi_id ")
    List<CoordisetVO> getLatestEightCodiSets();

    // 코디 세트 & 연결된 상품 리스트를 가져오는 메서드 (10개로 제한)
    @Select("SELECT c.codi_id, c.codi_name, c.codi_description, c.codi_thumbnail, c.codi_price " +
            "FROM codi c " +
            "GROUP BY c.codi_id " +
            "ORDER BY c.codi_id " +
            "Limit 10")
    @Results({
            @Result(property = "codiId", column = "codi_id"),
            @Result(property = "codiName", column = "codi_name"),
            @Result(property = "codiDescription", column = "codi_description"),
            @Result(property = "codiThumbnail", column = "codi_thumbnail"),
            @Result(property = "codiPrice", column = "codi_price"),
            @Result(property = "products", javaType = List.class, column = "codi_id",
                    many = @Many(select = "getProductsByCodiId"))
    })
    List<CoordiProductsDTO> getAllCoordiWithProducts();

    // 특정 코디 ID에 속하는 모든 상품 조회
    @Select("SELECT * FROM products WHERE codi_id = #{codiId}")
    List<ProductVO> getProductsByCodiId(int codiId);

}
