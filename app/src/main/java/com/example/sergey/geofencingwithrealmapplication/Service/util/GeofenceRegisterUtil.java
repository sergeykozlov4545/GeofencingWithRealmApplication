package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

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

    public void registerAllRegions() {
        List<Region> regions = regionsDatabase.getRegions();
        if (!regions.isEmpty()) {
            addGeofences(regions);
        }
    }

    public void unregisterAllRegions() {
        removeGeofences();
        regionsDatabase.unregisterAllRegions();
    }

    public void registerNearRegions(@NonNull Location location, @NonNull GeofencingEvent geofencingEvent) {
        Region lastRegion = regionsDatabase.getRegion(getFirstRegionId(geofencingEvent));
        if (lastRegion == null) {
            return;
        }

        List<Region> nearRegions = NearRegionsUtil.getNearRegions(lastRegion, location);
        if (!nearRegions.isEmpty()) {
            addGeofences(nearRegions);
        }

        RealmList<String> registeredRegionsNames = new RealmList<>();
        for (Region region : nearRegions) {
            registeredRegionsNames.add(region.getName());
        }

        LogEventDataBase.getInstance()
                .addEvent(
                        geofencingEvent.getGeofenceTransition(),
                        lastRegion.getName(),
                        registeredRegionsNames
                );
    }

    @NonNull
    private String getFirstRegionId(@NonNull GeofencingEvent geofencingEvent) {
        List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
        return geofences.get(0).getRequestId();
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
