package com.example.listykrajeprodukty;

public class Product {
    private String productName;
    private Float productPrice;

    public Product(String productName, Float productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getproductName() {
        return productName;
    }

    public Float getproductPrice() {
        return productPrice;
    }
}
