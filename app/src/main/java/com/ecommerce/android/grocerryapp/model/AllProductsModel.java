package com.ecommerce.android.grocerryapp.model;

import java.io.Serializable;

public class AllProductsModel implements Serializable {
    String img_url;
    String rating;
    String desp;
    static String name;
    String type;
    String price;

    public AllProductsModel(String img_url, String rating, String desp, String name, String type,String price) {
        this.img_url = img_url;
        this.rating = rating;
        this.desp = desp;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public AllProductsModel() {
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDesp() {
        return desp;
    }

    public  String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
