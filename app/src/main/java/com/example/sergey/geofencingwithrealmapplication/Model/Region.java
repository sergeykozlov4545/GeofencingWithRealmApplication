package com.example.sergey.geofencingwithrealmapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import io.realm.RealmObject;

public class Region extends RealmObject implements Parcelable {

    private String id;
    private String name;
    private RealmLatLng center;
    private int radius;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeParcelable(center, flags);
        parcel.writeInt(radius);
    }

    private Region(Parcel in) {
        name = in.readString();
        center = in.readParcelable(LatLng.class.getClassLoader());
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
