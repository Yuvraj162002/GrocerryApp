package com.ecommerce.android.grocerryapp.model;

public class SecondCategoryModel {

    String name;
    String img_url;
    String price;
    String type;

    public SecondCategoryModel() {
    }

    public SecondCategoryModel(String name, String img_url, String price, String type) {
        this.name = name;
        this.img_url = img_url;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }
}
