package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class GeofenceRegisterUtil {

    private RegionsDatabase regionsDatabase = RegionsDatabase.getInstance();

    private PendingIntent pendingIntent;
    private GeofencingClient geofencingClient;

    public GeofenceRegisterUtil(@NonNull Context context) {
        Intent intent = new Intent(context, GeofenceService.class);
        pendingIntent = PendingIntent.getService(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        geofencingClient = LocationServices.getGeofencingClient(context.getApplicationContext());
    }

    public void unregisterAllRegions() {
        removeGeofences();
        regionsDatabase.removeAllRegisteredRegions();
    }

    public void registerNearRegions(@NonNull Location location, @NonNull String regionId) {
        Region lastRegion = RegionsDatabase.getInstance().getRegion(regionId);
        if (lastRegion == null) {
            return;
        }

        List<Region> nearRegions = NearRegionsUtil.getNearRegions(lastRegion, location);
        if (nearRegions.isEmpty()) {
            return;
        }

        addGeofences(getGeofenceList(nearRegions));
        RegionsDatabase.getInstance().addRegisteredRegion(nearRegions);
    }

    @NonNull
    private List<Geofence> getGeofenceList(@NonNull List<Region> regions) {
        List<Geofence> geofences = new ArrayList<>();
        for (Region region : regions) {
            geofences.add(region.toGeofence());
        }
        return geofences;
    }

    @SuppressLint("MissingPermission")
    private void addGeofences(@NonNull List<Geofence> geofences) {
        geofencingClient.addGeofences(getGeofencingRequest(geofences), pendingIntent);
    }

    @NonNull
    private GeofencingRequest getGeofencingRequest(@NonNull List<Geofence> geofences) {
        return new GeofencingRequest.Builder()
                .addGeofences(geofences)
                .build();
    }

    private void removeGeofences() {
        geofencingClient.removeGeofences(regionsDatabase.getRegisteredIds());
    }
}
