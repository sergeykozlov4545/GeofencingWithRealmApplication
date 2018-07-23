package com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public interface EditRegionDialogView extends DialogView {
    void updateView(@NonNull Region region);
}
