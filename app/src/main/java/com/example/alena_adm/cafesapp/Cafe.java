package com.example.alena_adm.cafesapp;

/**
 * Created by Alena_Adm on 26.05.2018.
 */

public class Cafe {

    private String name;
    private String note;
    private String address;
    private int rating;
    private int id;

    public Cafe(String name, int id, String note, String address, int rating)
    {
        this.name = name;
        this.id = id;
        this.address = address;
        this.rating = rating;
        this.note = note;
    }
    public String getNote()
    {
        return this.note;
    }
    public String getAddress()
    {
        return this.address;
    }
    public int getRating()
    {
        return this.rating;
    }
    public String getName()
    {
        return this.name;
    }
    public int getId()
    {
        return this.id;
    }
}
