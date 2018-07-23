package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.AddRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public class AddRegionDialogPresenterImpl extends BasePresenter<DialogView> implements AddRegionDialogPresenter {

    @NonNull
    private RegionsDatabase regionsDatabase;

    public AddRegionDialogPresenterImpl(@NonNull RegionsDatabase regionsDatabase) {
        this.regionsDatabase = regionsDatabase;
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void onConfirmAddRegionButtonClick(@NonNull String name, double latitude, double longitude, int radius) {
        regionsDatabase.addRegion(name, latitude, longitude, radius);
    }
}
