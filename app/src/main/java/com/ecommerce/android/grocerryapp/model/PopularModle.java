package com.ecommerce.android.grocerryapp.model;

public class PopularModle {
    String name;
    String desciption;
    String rating;
    String discount;
    String img_url;
    String type;

    public PopularModle() {
    }

    public PopularModle(String name, String desciption, String rating, String discount, String img_url, String type) {
        this.name = name;
        this.desciption = desciption;
        this.rating = rating;
        this.discount = discount;
        this.img_url = img_url;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getRating() {
        return rating;
    }

    public String getDiscount() {
        return discount;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getType() {
        return type;
    }
}
