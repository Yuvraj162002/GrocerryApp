package com.ecommerce.android.grocerryapp.model;

public class NavcategoryModel {

    String name;
    String desp;
    String disc;
    String img_url;
    String type;

    public NavcategoryModel() {
    }

    public NavcategoryModel(String name, String desp, String disc, String img_url, String type) {
        this.name = name;
        this.desp = desp;
        this.disc = disc;
        this.img_url = img_url;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDesp() {
        return desp;
    }

    public String getDisc() {
        return disc;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getType() {
        return type;
    }
}