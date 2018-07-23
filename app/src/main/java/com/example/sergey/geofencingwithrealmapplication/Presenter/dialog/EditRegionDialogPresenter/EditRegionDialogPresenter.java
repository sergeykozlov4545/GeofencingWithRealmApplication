package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.EditRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RealmLatLng;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog.EditRegionDialogView;

public interface EditRegionDialogPresenter extends MVPPresenter<EditRegionDialogView> {
    void onDialogShowed(@NonNull String regionId);

    void onConfirmEditRegionButtonClick(@NonNull String regionId, @NonNull String name, @NonNull RealmLatLng center, int radius);
}
