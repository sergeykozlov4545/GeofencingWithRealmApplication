package com.example.sergey.geofencingwithrealmapplication.View.dialog.base;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;

public interface RegionDialog extends DialogView {
    void sendGeofenceServiceEvent(@NonNull GeofenceService.TypeOperation typeOperation);
}
