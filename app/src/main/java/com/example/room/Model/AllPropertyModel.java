package com.example.room.Model;

public class AllPropertyModel {
    private int imageId;
    private String title;
    private String location;
    private String amount;

    public AllPropertyModel() {
    }

    public AllPropertyModel(int imageId, String title, String location, String amount) {
        this.imageId = imageId;
        this.title = title;
        this.location = location;
        this.amount = amount;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
