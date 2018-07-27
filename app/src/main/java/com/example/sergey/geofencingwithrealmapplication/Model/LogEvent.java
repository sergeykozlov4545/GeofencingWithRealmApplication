package com.example.sergey.geofencingwithrealmapplication.Model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class LogEvent extends RealmObject {

    private String id;
    private int transitionType;
    private String regionName;
    private RealmList<String> registeredRegions;

    public LogEvent() {
    }

    public LogEvent(String id, int transitionType, String regionName, RealmList<String> registeredRegions) {
        this.id = id;
        this.transitionType = transitionType;
        this.regionName = regionName;
        this.registeredRegions = registeredRegions;
    }

    public String getId() {
        return id;
    }

    public int getTransitionType() {
        return transitionType;
    }

    public String getRegionName() {
        return regionName;
    }

    public RealmList<String> getRegisteredRegions() {
        return registeredRegions;
    }
}
