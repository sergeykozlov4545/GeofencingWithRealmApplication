package com.example.sergey.geofencingwithrealmapplication.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Model.TrackPreference;
import com.example.sergey.geofencingwithrealmapplication.Service.util.GeofenceRegisterUtil;
import com.example.sergey.geofencingwithrealmapplication.Service.util.GeofencingEventParser;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcast extends BroadcastReceiver {

    public static final String ACTION = "com.example.sergey.geofencingwithrealmapplication.Service.receiver_action";
    public static final String TYPE_OPERATION_EXTRA = "type_operation";

    public enum TypeOperation {
        REGISTER_ALL_REGIONS,
        UNREGISTER_ALL_REGIONS,
        REREGISTER_REGIONS
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }

        Context applicationContext = context.getApplicationContext();

        GeofenceRegisterUtil geofenceRegisterUtil = new GeofenceRegisterUtil(applicationContext);

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
                TrackPreference trackPreference = new TrackPreference(applicationContext);
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
}
