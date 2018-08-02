package com.example.sergey.geofencingwithrealmapplication.View.dialog.base;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceBroadcast;

public interface RegionDialog extends DialogView {
    void sendGeofenceBroadcastEvent(@NonNull GeofenceBroadcast.TypeOperation typeOperation);
}
