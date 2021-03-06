package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.AddRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RealmLatLng;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.RegionDialog;

public interface AddRegionDialogPresenter extends MVPPresenter<RegionDialog> {
    void onConfirmAddRegionButtonClick(@NonNull String name, @NonNull RealmLatLng center, int radius);

    void onNegativeButtonClick();
}
