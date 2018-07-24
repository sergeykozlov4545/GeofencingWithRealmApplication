package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

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

    public void registerNearRegions(@NonNull Location location) {
        // TODO: 24.07.18 Получить из базы ближайшие положению зоны
        // TODO: 24.07.18 Зарегать их если они есть (addGeofences)
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
