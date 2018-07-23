package com.example.sergey.geofencingwithrealmapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class Region extends RealmObject implements Parcelable {

    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private int radius;

    public Region() {
    }

    public Region(String id, String name, double latitude, double longitude, int radius) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public String getId() {
        return id;
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

    public int getRadius() {
        return radius;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeInt(radius);
    }

    private Region(Parcel in) {
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        radius = in.readInt();
    }

    public static final Creator<Region> CREATOR = new Creator<Region>() {
        @Override
        public Region createFromParcel(Parcel in) {
            return new Region(in);
        }

        @Override
        public Region[] newArray(int size) {
            return new Region[size];
        }
    };

}
