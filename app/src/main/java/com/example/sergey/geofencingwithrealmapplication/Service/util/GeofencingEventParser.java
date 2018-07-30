package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofencingEventParser {

    private boolean hasError;

    private int transitionType;

    @Nullable
    private Geofence firstTriggeringGeofence;

    @NonNull
    private Location triggeringLocation;

    public GeofencingEventParser(@NonNull GeofencingEvent event) {
        this.hasError = event.hasError();
        this.transitionType = event.getGeofenceTransition();
        this.firstTriggeringGeofence = event.getTriggeringGeofences().size() > 0
                ? event.getTriggeringGeofences().get(0) : null;
        this.triggeringLocation = event.getTriggeringLocation();
    }

    public boolean hasError() {
        return hasError;
    }

    public boolean isTransitionEnter() {
        return transitionType == Geofence.GEOFENCE_TRANSITION_ENTER;
    }

    @Nullable
    public Geofence getFirstTriggeringGeofence() {
        return firstTriggeringGeofence;
    }

    @NonNull
    public Location getTriggeringLocation() {
        return triggeringLocation;
    }
}
