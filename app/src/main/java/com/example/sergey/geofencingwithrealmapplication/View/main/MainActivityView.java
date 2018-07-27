package com.example.sergey.geofencingwithrealmapplication.View.main;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

public interface MainActivityView extends MVPView {
    void updateTrackState(boolean trackZones);

    void updateTrackButtonText();

    void sendGeofenceServiceEvent(@NonNull GeofenceService.TypeOperation typeOperation);

    void showEditPointActivity();
}
