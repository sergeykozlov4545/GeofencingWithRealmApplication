package com.example.sergey.geofencingwithrealmapplication.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Service.util.GeofenceRegisterUtil;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceService extends IntentService {

    private static final String TAG = "GeofenceService";

    private GeofenceRegisterUtil geofenceRegisterUtil;

    public GeofenceService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        geofenceRegisterUtil = new GeofenceRegisterUtil(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent == null || geofencingEvent.hasError()) {
            return;
        }

        if (isTransitionEnter(geofencingEvent.getGeofenceTransition())) {
            // TODO: 24.07.18 Вошли в зону, а значит чето делаем (notification, лог на MainActivity и т.д.)
            return;
        }

        geofenceRegisterUtil.unregisterAllRegions();
        geofenceRegisterUtil.registerNearRegions(
                geofencingEvent.getTriggeringLocation(), getLastRegionId(geofencingEvent));
    }

    private boolean isTransitionEnter(int transitionType) {
        return transitionType == Geofence.GEOFENCE_TRANSITION_ENTER;
    }

    @NonNull
    private String getLastRegionId(@NonNull GeofencingEvent geofencingEvent) {
        List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
        return geofences.get(0).getRequestId();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
