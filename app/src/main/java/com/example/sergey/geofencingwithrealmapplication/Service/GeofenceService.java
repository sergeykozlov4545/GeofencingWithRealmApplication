package com.example.sergey.geofencingwithrealmapplication.Service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Service.util.LocationUtil;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceService extends IntentService {

    private static final String TAG = "GeofenceService";

    private LocationUtil locationUtil;

    public GeofenceService() {
        super(TAG);

        locationUtil = new LocationUtil(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent != null && !geofencingEvent.hasError()) {
            if (isTransitionEnter(geofencingEvent.getGeofenceTransition())) {
                // TODO: 24.07.18 Вошли в зону, а значит чето делаем (notification, лог на MainActivity и т.д.)
                return;
            }

            locationUtil.requestLocationUpdates();
        }
    }

    private boolean isTransitionEnter(int transitionType) {
        return transitionType == Geofence.GEOFENCE_TRANSITION_ENTER;
    }
}
