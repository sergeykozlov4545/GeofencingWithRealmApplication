package com.example.sergey.geofencingwithrealmapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Model.TrackPreference;
import com.example.sergey.geofencingwithrealmapplication.Service.util.GeofenceRegisterUtil;
import com.example.sergey.geofencingwithrealmapplication.Service.util.GeofencingEventParser;
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

    public enum TypeOperation {
        REGISTER_ALL_REGIONS,
        UNREGISTER_ALL_REGIONS,
        REREGISTER_REGIONS
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
                TrackPreference trackPreference = new TrackPreference(getApplicationContext());
                if (trackPreference.getTrackState()) {
                    geofenceRegisterUtil.unregisterAllRegions();
                    geofenceRegisterUtil.registerAllRegions();
                }
            }

            return;
        }

        GeofencingEventParser parser = new GeofencingEventParser(GeofencingEvent.fromIntent(intent));

        if (parser.hasError()) {
            return;
        }

        if (parser.isTransitionEnter()) {
            if (parser.getFirstTriggeringGeofence() == null) {
                return;
            }

            Region triggeringRegion = RegionsDatabase.getInstance()
                    .getRegion(parser.getFirstTriggeringGeofence().getRequestId());

            if (triggeringRegion != null) {
                List<Region> regions = RegionsDatabase.getInstance().getRegisteredRegions();

                LogEventDataBase.getInstance()
                        .addEvent(parser.getTransitionType(), triggeringRegion, regions);
            }

            return;
        }

        geofenceRegisterUtil.unregisterAllRegions();
        geofenceRegisterUtil.registerNearRegions(parser);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
