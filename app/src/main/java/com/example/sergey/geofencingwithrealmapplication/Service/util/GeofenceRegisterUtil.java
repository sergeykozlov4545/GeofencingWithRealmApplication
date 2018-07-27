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
        regionsDatabase.unregisterAllRegions();
    }

    public void registerNearRegions(@NonNull Location location, @NonNull String regionId) {
        Region lastRegion = regionsDatabase.getRegion(regionId);
        if (lastRegion == null) {
            return;
        }

        List<Region> nearRegions = NearRegionsUtil.getNearRegions(lastRegion, location);
        if (!nearRegions.isEmpty()) {
            addGeofences(nearRegions);
        }
    }

    private void removeGeofences() {
        List<String> registeredIds = new ArrayList<>();

        for (Region region : regionsDatabase.getRegisteredRegions()) {
            registeredIds.add(region.getId());
        }

        geofencingClient.removeGeofences(registeredIds);
    }

    @SuppressLint("MissingPermission")
    private void addGeofences(@NonNull List<Region> regions) {
        List<Geofence> geofences = mapToGeofence(regions);

        geofencingClient.addGeofences(getGeofencingRequest(geofences), pendingIntent)
                .addOnSuccessListener(aVoid -> regionsDatabase.registerRegions(regions));
    }

    @NonNull
    private List<Geofence> mapToGeofence(@NonNull List<Region> regions) {
        List<Geofence> geofences = new ArrayList<>();
        for (Region region : regions) {
            geofences.add(region.toGeofence());
        }
        return geofences;
    }

    @NonNull
    private GeofencingRequest getGeofencingRequest(@NonNull List<Geofence> geofences) {
        return new GeofencingRequest.Builder()
                .addGeofences(geofences)
                .build();
    }
}
