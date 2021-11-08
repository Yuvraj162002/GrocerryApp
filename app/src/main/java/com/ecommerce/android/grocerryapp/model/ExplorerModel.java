package com.ecommerce.android.grocerryapp.model;

public class ExplorerModel {
    String name;
    String img_url;
    String type;

    public ExplorerModel() {
    }

    public ExplorerModel(String name, String img_url, String type) {
        this.name = name;
        this.img_url = img_url;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getType() {
        return type;
    }
}
