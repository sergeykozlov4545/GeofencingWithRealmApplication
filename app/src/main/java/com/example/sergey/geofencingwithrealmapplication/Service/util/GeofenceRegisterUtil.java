package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Model.RealmLatLng;
import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

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

    public void registerAllRegions() {
        List<Region> regions = regionsDatabase.getRegions();
        if (!regions.isEmpty()) {
            addGeofences(regions, new BaseRegisterRegionsOnSuccessListener(regions));
        }
    }

    public void unregisterAllRegions() {
        removeGeofences();
        regionsDatabase.unregisterAllRegions();
    }

    public void registerNearRegions(@NonNull GeofencingEventParser parser) {
        if (parser.getFirstTriggeringGeofence() == null) {
            return;
        }

        Region lastRegion = regionsDatabase.getRegion(parser.getFirstTriggeringGeofence().getRequestId());
        if (lastRegion == null) {
            return;
        }

        List<Region> nearRegions =
                NearRegionsUtil.getNearRegions(lastRegion, parser.getTriggeringLocation());
        nearRegions.add(lastRegion);

        addGeofences(nearRegions,
                new RegisterNearRegionsOnSuccessListener(lastRegion, nearRegions, parser));
    }

    private void removeGeofences() {
        List<String> registeredIds = new ArrayList<>();
        for (Region region : regionsDatabase.getRegisteredRegions()) {
            registeredIds.add(region.getId());
        }
        if (!registeredIds.isEmpty()) {
            geofencingClient.removeGeofences(registeredIds);
        }
    }

    @SuppressLint("MissingPermission")
    private void addGeofences(@NonNull List<Region> regions,
                              @NonNull OnSuccessListener<? super Void> onSuccessListener) {
        List<Geofence> geofences = mapToGeofence(regions);

        geofencingClient.addGeofences(getGeofencingRequest(geofences), pendingIntent)
                .addOnSuccessListener(onSuccessListener);
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

    private class BaseRegisterRegionsOnSuccessListener implements OnSuccessListener<Void> {

        @NonNull
        private List<Region> regions;

        BaseRegisterRegionsOnSuccessListener(@NonNull List<Region> regions) {
            this.regions = regions;
        }

        @Override
        public void onSuccess(Void aVoid) {
            regionsDatabase.registerRegions(regions);
        }

        @NonNull
        List<Region> getRegions() {
            return regions;
        }
    }

    private class RegisterNearRegionsOnSuccessListener extends BaseRegisterRegionsOnSuccessListener {

        @NonNull
        private Region lastRegion;

        @NonNull
        private GeofencingEventParser parser;

        RegisterNearRegionsOnSuccessListener(@NonNull Region lastRegion,
                                             @NonNull List<Region> regions,
                                             @NonNull GeofencingEventParser parser) {
            super(regions);
            this.lastRegion = lastRegion;
            this.parser = parser;
        }

        @Override
        public void onSuccess(Void aVoid) {
            super.onSuccess(aVoid);

            LogEventDataBase.getInstance()
                    .addEvent(parser.getTransitionType(), getNearRegion(), getRegions());
        }

        @Nullable
        private Region getNearRegion() {
            RealmLatLng currentPosition = new RealmLatLng(parser.getTriggeringLocation());

            Region nearRegion = null;

            for (Region region : getRegions()) {
                if (region.getId().equals(lastRegion.getId())) {
                    continue;
                }

                if (nearRegion == null) {
                    nearRegion = region;
                    continue;
                }

                RealmLatLng regionPosition = region.getCenter();
                RealmLatLng nearRegionPosition = nearRegion.getCenter();
                if (regionPosition.destination(currentPosition) < nearRegionPosition.destination(currentPosition)) {
                    nearRegion = region;
                }
            }

            return nearRegion;
        }
    }
}
