package com.example.sergey.geofencingwithrealmapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Service.util.GeofenceRegisterUtil;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceService extends Service {

    public static final String TYPE_OPERATION_EXTRA = "type_operation";

    private GeofenceRegisterUtil geofenceRegisterUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        geofenceRegisterUtil = new GeofenceRegisterUtil(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        work(intent);
        stopSelf(startId);
        return START_STICKY;
    }

    private void work(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        if (intent.hasExtra(TYPE_OPERATION_EXTRA)) {
            TypeOperation typeOperation
                    = (TypeOperation) intent.getSerializableExtra(TYPE_OPERATION_EXTRA);

            if (typeOperation == TypeOperation.REGISTER_ALL_REGIONS) {
                geofenceRegisterUtil.registerAllRegions();
                return;
            }

            if (typeOperation == TypeOperation.UNREGISTER_ALL_REGIONS) {
                geofenceRegisterUtil.unregisterAllRegions();
                return;
            }

            if (typeOperation == TypeOperation.REREGISTER_REGIONS) {
                geofenceRegisterUtil.unregisterAllRegions();
                geofenceRegisterUtil.registerAllRegions();
                return;
            }

            return;
        }

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            return;
        }

        if (isTransitionEnter(geofencingEvent.getGeofenceTransition())) {
            // TODO: 24.07.18 Вошли в зону, а значит чето делаем (notification, лог на MainActivity и т.д.)
            return;
        }

        geofenceRegisterUtil.unregisterAllRegions();
        geofenceRegisterUtil.registerNearRegions(
                geofencingEvent.getTriggeringLocation(), getFirstRegionId(geofencingEvent));
    }

    private boolean isTransitionEnter(int transitionType) {
        return transitionType == Geofence.GEOFENCE_TRANSITION_ENTER;
    }

    @NonNull
    private String getFirstRegionId(@NonNull GeofencingEvent geofencingEvent) {
        List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
        return geofences.get(0).getRequestId();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public enum TypeOperation {
        REGISTER_ALL_REGIONS,
        UNREGISTER_ALL_REGIONS,
        REREGISTER_REGIONS
    }
}
