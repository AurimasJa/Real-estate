package edu.ktu.myfirstapplication;

public class Skelbimas {

    String title,description,price,place,room_count;
    int imageId;


    public Skelbimas(String title, String description, String price, String place, String room_count, int imageId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.place = place;
        this.room_count = room_count;
        this.imageId = imageId;
    }
}
