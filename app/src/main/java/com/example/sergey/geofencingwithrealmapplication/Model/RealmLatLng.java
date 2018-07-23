package com.example.sergey.geofencingwithrealmapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class RealmLatLng extends RealmObject implements Parcelable {

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

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", latitude, longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    public static final Creator<RealmLatLng> CREATOR = new Creator<RealmLatLng>() {
        @Override
        public RealmLatLng createFromParcel(Parcel in) {
            return new RealmLatLng(in);
        }

        @Override
        public RealmLatLng[] newArray(int size) {
            return new RealmLatLng[size];
        }
    };

    private RealmLatLng(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }
}
