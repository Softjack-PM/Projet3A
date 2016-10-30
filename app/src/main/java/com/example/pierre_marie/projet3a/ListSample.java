package com.example.pierre_marie.projet3a;

/**
 * Created by Pierre-Marie on 30/10/2016.
 */

public class ListSample {

    private String name = null;
    private double latitude = 0;
    private double longitude = 0;

    public ListSample(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
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
}
