package com.ecommerce.android.grocerryapp.model;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String ProductName;
    String ProductPrice;
    String currentDate;
    String currentTime;
    int totalprice;
    String documentId;
    String TotalQuantity;

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public MyCartModel() {
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public MyCartModel(String productName, String productPrice, String currentDate, String currentTime, int totalprice, String totalQuantity) {
        ProductName = productName;
        ProductPrice = productPrice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalprice = totalprice;
        TotalQuantity = totalQuantity;
    }
}