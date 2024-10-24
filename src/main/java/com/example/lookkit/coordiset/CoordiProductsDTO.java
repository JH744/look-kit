package com.example.lookkit.coordiset;

import com.example.lookkit.product.ProductVO;

import java.util.List;

public class CoordiProductsDTO {
    private int codiId;
    private String codiName;
    private String codiDescription;
    private String codiThumbnail;
    private int codiPrice;
    private List<ProductVO> products; // ProductVO도 DTO로 변환

    // Getters and Setters
    public int getCodiId() { return codiId; }
    public void setCodiId(int codiId) { this.codiId = codiId; }

    public String getCodiName() { return codiName; }
    public void setCodiName(String codiName) { this.codiName = codiName; }

    public String getCodiDescription() { return codiDescription; }
    public void setCodiDescription(String codiDescription) { this.codiDescription = codiDescription; }

    public String getCodiThumbnail() { return codiThumbnail; }
    public void setCodiThumbnail(String codiThumbnail) { this.codiThumbnail = codiThumbnail; }

    public int getCodiPrice() { return codiPrice; }
    public void setCodiPrice(int codiPrice) { this.codiPrice = codiPrice; }

    public List<ProductVO> getProducts() { return products; }
    public void setProducts(List<ProductVO> products) { this.products = products; }
}