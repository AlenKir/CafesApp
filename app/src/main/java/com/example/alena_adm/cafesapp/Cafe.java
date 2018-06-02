package com.example.alena_adm.cafesapp;

/**
 * Created by Alena_Adm on 27.05.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cafe {

    @SerializedName("cafe_name")
    @Expose
    public String cafe_name;

    @SerializedName("cafe_note")
    @Expose
    public String cafe_note;

    @SerializedName("cafe_address")
    @Expose
    public String cafe_address;

    @SerializedName("cafe_rating")
    @Expose
    public int cafe_rating;

    @SerializedName("cafe_id")
    @Expose
    public int cafe_id;

    public Cafe(int cafe_id, String cafe_name, String cafe_note, String cafe_address, int cafe_rating) {
        this.cafe_name = cafe_name;
        this.cafe_id = cafe_id;
        this.cafe_address = cafe_address;
        this.cafe_rating = cafe_rating;
        this.cafe_note = cafe_note;
    }

    public String getNote() {
        return this.cafe_note;
    }

    public String getAddress() {
        return this.cafe_address;
    }

    public int getRating() {
        return this.cafe_rating;
    }

    public String getName() {
        return this.cafe_name;
    }

    public int getId() {
        return this.cafe_id;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "cafe_name='" + cafe_name + '\'' +
                ", cafe_note='" + cafe_note + '\'' +
                ", cafe_address='" + cafe_address + '\'' +
                ", cafe_rating=" + cafe_rating +
                '}';
    }
}
