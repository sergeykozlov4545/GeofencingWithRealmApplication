package com.example.sergey.geofencingwithrealmapplication.View.main;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEvent;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceBroadcast;
import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

import io.realm.OrderedRealmCollection;

public interface MainActivityView extends MVPView {
    void updateTrackButton(boolean trackState);

    void showMessage(@StringRes int messageRes);

    void updateLogData(@NonNull OrderedRealmCollection<LogEvent> logEvents);

    void sendGeofenceBroadcastEvent(@NonNull GeofenceBroadcast.TypeOperation typeOperation);

    void showEditPointActivity();

    void showClearLogDialog();
}
