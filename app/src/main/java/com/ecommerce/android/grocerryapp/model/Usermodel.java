package com.ecommerce.android.grocerryapp.model;

import android.widget.EditText;

public class Usermodel {
    String mail;
    String pass;
    String name;
    String profile_picture;

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public Usermodel() {
    }

    public Usermodel(String mail, String pass, String name) {
        this.mail = mail;
        this.pass = pass;
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

//    public Object getProfileImg() {
//
//        return getProfileImg();
//    }
}
