package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.EditRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog.EditRegionDialogView;
import com.google.android.gms.maps.model.LatLng;

public interface EditRegionDialogPresenter extends MVPPresenter<EditRegionDialogView> {
    void onDialogShowed(@NonNull String regionId);

    void onConfirmEditRegionButtonClick(@NonNull String regionId,
                                        @NonNull String name,
                                        @NonNull LatLng center,
                                        int radius);
}
