package com.example.lookkit.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ProductVO getProductById(int productId) {
        ProductVO product = productMapper.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }

    public boolean isStockAvailable(int productId, int quantity) {
        int currentStock = productMapper.getProductStock(productId);
        return currentStock >= quantity;
    }

    public void updateProductStock(int productId, int quantity) {
        int updatedRows = productMapper.updateProductStock(productId, quantity);
        if (updatedRows == 0) {
            throw new RuntimeException("Failed to update product stock or not enough stock available");
        }
    }
}