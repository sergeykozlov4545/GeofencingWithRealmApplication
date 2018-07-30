package com.example.sergey.geofencingwithrealmapplication.Model;

import android.location.Location;
import android.support.annotation.NonNull;

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

    public RealmLatLng(@NonNull Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
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

    public double destination(RealmLatLng otherPoint) {
        double x = otherPoint.latitude - latitude;
        double y = otherPoint.longitude - longitude;
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", latitude, longitude);
    }
}
