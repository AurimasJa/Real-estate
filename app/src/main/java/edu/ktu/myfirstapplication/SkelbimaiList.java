package edu.ktu.myfirstapplication;

import android.widget.ImageView;

public class SkelbimaiList {
    private String title;
    private int imageId;
    private String description;
    private float price;
    private int room_count;
    private String phoneNum;
    private String createdBy;
    //private ImageView[] images;

    public SkelbimaiList() {
    }

    public SkelbimaiList(String title, int imageId, String description, float price, int room_count, String phoneNum, String createdBy) {
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.price = price;
        this.room_count = room_count;
        this.phoneNum = phoneNum;
        this.createdBy = createdBy;
        //this.images = images;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public float getPrice() {
        return price;
    }

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
