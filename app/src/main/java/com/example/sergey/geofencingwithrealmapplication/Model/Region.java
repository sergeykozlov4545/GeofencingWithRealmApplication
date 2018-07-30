package com.example.sergey.geofencingwithrealmapplication.Model;

import android.support.annotation.NonNull;

import com.google.android.gms.location.Geofence;

import io.realm.RealmObject;

public class Region extends RealmObject {
    private static final int TWELVE_HOURS = 12 * 60 * 60 * 1000;

    private String id;
    private String name;
    private RealmLatLng center;
    private int radius;
    private boolean registered;

    public Region() {
    }

    public Region(String id, String name, RealmLatLng center, int radius) {
        this.id = id;
        this.name = name;
        this.center = center;
        this.radius = radius;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmLatLng getCenter() {
        return center;
    }

    public void setCenter(RealmLatLng center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    @NonNull
    public Geofence toGeofence() {
        return new Geofence.Builder()
                .setRequestId(id)
                .setCircularRegion(center.getLatitude(), center.getLongitude(), radius)
                .setExpirationDuration(TWELVE_HOURS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }
}
