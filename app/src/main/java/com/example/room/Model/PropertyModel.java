package com.example.room.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PropertyModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("posted_date")
    @Expose
    private String postedDate;
    @SerializedName("property_image")
    @Expose
    private String propertyImage;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("property_type")
    @Expose
    private String propertyType;
    @SerializedName("property_location")
    @Expose
    private String propertyLocation;
    @SerializedName("property_size")
    @Expose
    private String propertySize;
    @SerializedName("property_rent")
    @Expose
    private String propertyRent;
    @SerializedName("available_from")
    @Expose
    private String availableFrom;
    @SerializedName("furniture_detail")
    @Expose
    private String furnitureDetail;
    @SerializedName("bathrooms")
    @Expose
    private Integer bathrooms;
    @SerializedName("bedrooms")
    @Expose
    private Integer bedrooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public String getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(String propertySize) {
        this.propertySize = propertySize;
    }

    public String getPropertyRent() {
        return propertyRent;
    }

    public void setPropertyRent(String propertyRent) {
        this.propertyRent = propertyRent;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getFurnitureDetail() {
        return furnitureDetail;
    }

    public void setFurnitureDetail(String furnitureDetail) {
        this.furnitureDetail = furnitureDetail;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

}
