package com.example.alena_adm.cafesapp;

/**
 * Created by Alena_Adm on 27.05.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cafe {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("note")
    @Expose
    public String note;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("rating")
    @Expose
    public int rating;
    @SerializedName("id")
    @Expose
    public int id;

    public Cafe(String name, int id, String note, String address, int rating) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.rating = rating;
        this.note = note;
    }

    public String getNote() {
        return this.note;
    }

    public String getAddress() {
        return this.address;
    }

    public int getRating() {
        return this.rating;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                '}';
    }
}
