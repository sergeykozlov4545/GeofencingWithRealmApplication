package com.example.sergey.geofencingwithrealmapplication.View.dialog.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceBroadcast;

public class RegionDialogImpl extends DialogViewImpl implements RegionDialog {

    @Override
    public void sendGeofenceBroadcastEvent(@NonNull GeofenceBroadcast.TypeOperation typeOperation) {
        Context context = getContext();
        if (context != null) {
            context.sendBroadcast(new Intent(GeofenceBroadcast.ACTION)
                    .putExtra(GeofenceBroadcast.TYPE_OPERATION_EXTRA, typeOperation));
        }
    }
}
