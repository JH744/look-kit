package com.example.lookkit.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ProductVO getProductById(long productId) {
        return productMapper.getProductById(productId);
    }
}