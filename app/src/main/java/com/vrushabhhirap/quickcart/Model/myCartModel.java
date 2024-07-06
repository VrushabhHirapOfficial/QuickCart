package com.vrushabhhirap.quickcart.Model;

import java.io.Serializable;

public class myCartModel implements Serializable {

    String ProductName;
    String ProductPrice;
    int totalPrice;
    String totalQuantity;
    String ProductImage;
    String ProductDescription;
    String ProductRating;
    String ProductType;
    String LastTotal;
    String ProductId;

    public myCartModel() {
    }

    public myCartModel(String productName, String productPrice, int totalPrice,
                       String totalQuantity,String ProductImage,String ProductDescription,
                       String ProductRating,String ProductType,String LastTotal,String ProductId) {
        ProductName = productName;
        ProductPrice = productPrice;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.ProductImage = ProductImage;
        this.ProductDescription = ProductDescription;
        this.ProductRating = ProductRating;
        this.ProductType = ProductType;
        this.LastTotal = LastTotal;
        this.ProductId = ProductId;

    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductRating() {
        return ProductRating;
    }

    public void setProductRating(String productRating) {
        ProductRating = productRating;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getLastTotal() {
        return LastTotal;
    }

    public void setLastTotal(String lastTotal) {
        LastTotal = lastTotal;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }
}
