package edu.ktu.myfirstapplication;

import android.widget.ImageView;

import java.io.Serializable;

public class SkelbimaiList implements Serializable {
    private String title;
    private String description;
    private float price;
    private String location;
    private int room_count;
    private String phoneNum;
    private String createdBy;
    //private ImageView[] images;
    private String Image;
    private int template;

    public SkelbimaiList() {
    }

    public SkelbimaiList(String title, String Location, String description, float price, int room_count, String phoneNum, String createdBy, String image, int template) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = Location;
        this.room_count = room_count;
        this.phoneNum = phoneNum;
        this.createdBy = createdBy;
        //this.images = images;
        this.Image = image;
        this.template = template;
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRoom_count() {
        return room_count;
    }

    public void setRoom_count(int room_count) {
        this.room_count = room_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    //public ImageView[] getImages() { return images; }

    //public void setImages(ImageView[] images) { this.images = images; }
}
