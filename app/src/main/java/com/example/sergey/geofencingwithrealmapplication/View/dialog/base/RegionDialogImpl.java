package com.example.sergey.geofencingwithrealmapplication.View.dialog.base;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;

public class RegionDialogImpl extends DialogViewImpl implements RegionDialog {

    @Override
    public void sendGeofenceServiceEvent(@NonNull GeofenceService.TypeOperation typeOperation) {
        Intent intent = new Intent(getContext(), GeofenceService.class)
                .putExtra(GeofenceService.TYPE_OPERATION_EXTRA, typeOperation);
        getContext().startService(intent);
    }
}
