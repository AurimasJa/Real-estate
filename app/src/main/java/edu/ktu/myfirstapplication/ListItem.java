package edu.ktu.myfirstapplication;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ListItem implements Parcelable {
    private String title;
    private Bitmap image;
    private String description;

    public ListItem(String title, Bitmap image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    protected ListItem(Parcel in) {
        this.title = in.readString();
        image = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        this.description = in.readString();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel parcel) {
            return new ListItem(parcel);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImageId() {
        return image;
    }

    public void setImageId(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(title);
        parcel.writeValue(image);
        parcel.writeString(description);
    }
}
