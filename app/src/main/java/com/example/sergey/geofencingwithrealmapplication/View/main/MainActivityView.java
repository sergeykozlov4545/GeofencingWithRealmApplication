package com.example.sergey.geofencingwithrealmapplication.View.main;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEvent;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

import io.realm.OrderedRealmCollection;

public interface MainActivityView extends MVPView {
    void updateTrackState(boolean trackZones);

    void updateTrackButton();

    void showEnabledTrackLocationMessage();

    void showDisabledTrackLocationMessage();

    void updateLogData(@NonNull OrderedRealmCollection<LogEvent> logEvents);

    void sendGeofenceServiceEvent(@NonNull GeofenceService.TypeOperation typeOperation);

    void showEditPointActivity();

    void showClearLogDialog();
}
