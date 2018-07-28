package com.example.sergey.geofencingwithrealmapplication.Model;

import io.realm.RealmObject;

public class RealmLatLng extends RealmObject {

    private double latitude;
    private double longitude;

    public RealmLatLng() {
    }

    public RealmLatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", latitude, longitude);
    }
}
