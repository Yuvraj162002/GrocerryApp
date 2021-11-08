package com.ecommerce.android.grocerryapp.model;

public class RecommededModel {
    String name;
    String type;
    String img_url;
    String desp;
    String rating;

    public RecommededModel() {
    }

    public RecommededModel(String name, String type, String img_url, String desp, String rating) {
        this.name = name;
        this.type = type;
        this.img_url = img_url;
        this.desp = desp;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDesp() {
        return desp;
    }

    public String getRating() {
        return rating;
    }
}
