package com.example.room.Model;

import android.content.Intent;

public class AllPropertyModel {
    //private String imag;
    private int imag;
    private String title;
    private String location;
    private String amount;

    public AllPropertyModel() {
    }


    public AllPropertyModel( int imageId, String title, String location, String amount) {
        this.imag = imag;
        this.title = title;
        this.location = location;
        this.amount = amount;
    }

    public int getImageId() {
        return imag;
    }

    public void setImageId(int  imag) {
        this.imag = imag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
