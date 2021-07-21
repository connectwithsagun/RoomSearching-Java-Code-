package com.example.room.Model;

public class MyPropertyModel {
    private int Image;
    private String propertyType;
    private String propertyArea;
    private String propertyTitle;
    private String propertyLocation;
    private String status;

    public MyPropertyModel() {
    }

    public MyPropertyModel(int image, String propertyType, String propertyArea, String propertyTitle, String propertyLocation, String status) {
        Image = image;
        this.propertyType = propertyType;
        this.propertyArea = propertyArea;
        this.propertyTitle = propertyTitle;
        this.propertyLocation = propertyLocation;
        this.status = status;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
