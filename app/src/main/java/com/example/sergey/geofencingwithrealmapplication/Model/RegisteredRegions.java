package com.example.sergey.geofencingwithrealmapplication.Model;

import io.realm.RealmObject;

public class RegisteredRegions extends RealmObject {

    private Region region;

    public RegisteredRegions(Region region) {
        this.region = region;
    }

    public RegisteredRegions() {
    }

    public Region getRegion() {
        return region;
    }
}
