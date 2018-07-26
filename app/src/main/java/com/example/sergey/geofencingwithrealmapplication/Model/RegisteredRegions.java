package com.example.sergey.geofencingwithrealmapplication.Model;

import android.support.annotation.NonNull;

import io.realm.RealmObject;

public class RegisteredRegions extends RealmObject {

    private String regionId;

    public RegisteredRegions(@NonNull String regionId) {
        this.regionId = regionId;
    }

    public RegisteredRegions() {
    }

    public String getRegionId() {
        return regionId;
    }
}
