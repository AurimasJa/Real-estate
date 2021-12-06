package edu.ktu.myfirstapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;

public class SkelbimaiList implements Parcelable {
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

    protected SkelbimaiList(Parcel in) {
        title = in.readString();
        description = in.readString();
        price = in.readFloat();
        location = in.readString();
        room_count = in.readInt();
        phoneNum = in.readString();
        createdBy = in.readString();
        Image = in.readString();
        template = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeFloat(price);
        dest.writeString(location);
        dest.writeInt(room_count);
        dest.writeString(phoneNum);
        dest.writeString(createdBy);
        dest.writeString(Image);
        dest.writeInt(template);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SkelbimaiList> CREATOR = new Creator<SkelbimaiList>() {
        @Override
        public SkelbimaiList createFromParcel(Parcel in) {
            return new SkelbimaiList(in);
        }

        @Override
        public SkelbimaiList[] newArray(int size) {
            return new SkelbimaiList[size];
        }
    };

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
