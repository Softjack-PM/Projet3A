package com.example.pierre_marie.projet3a;

/**
 * Created by Pierre-Marie on 30/10/2016.
 */

import com.google.gson.annotations.SerializedName;

//Défini un élément Monument
public class Monument {

    @SerializedName("name")
    private String name;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;

    public Monument(){
    }

    public String getName(){
        return name;
    }
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Monument{" + "nom = " + name + "\nlatitude = " + latitude + "\nlongitude = " + longitude + "}";
    }
}