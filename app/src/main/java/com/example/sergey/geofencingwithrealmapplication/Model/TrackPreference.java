package com.example.sergey.geofencingwithrealmapplication.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class TrackPreference {

    private static final String SHARED_PREFERENCE_NAME = "shared_track_preference";
    private static final String TRACK_PREFERENCE_NAME = "track_preference";

    private SharedPreferences preferences;

    public TrackPreference(@NonNull Context context) {
        preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setTrackState(boolean trackState) {
        preferences.edit().putBoolean(TRACK_PREFERENCE_NAME, trackState).apply();
    }

    public boolean getTrackState() {
        return preferences.getBoolean(TRACK_PREFERENCE_NAME, false);
    }
}
