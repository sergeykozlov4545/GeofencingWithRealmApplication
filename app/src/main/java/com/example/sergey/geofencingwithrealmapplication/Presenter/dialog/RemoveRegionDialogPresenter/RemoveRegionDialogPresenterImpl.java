package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public class RemoveRegionDialogPresenterImpl
        extends BasePresenter<DialogView> implements RemoveRegionDialogPresenter {

    @NonNull
    private RegionsDatabase regionsDatabase;

    public RemoveRegionDialogPresenterImpl(@NonNull RegionsDatabase regionsDatabase) {
        this.regionsDatabase = regionsDatabase;
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void onConfirmRemoveRegionButtonClick(@NonNull String regionId) {
        regionsDatabase.removeRegion(regionId);
        DialogView view = getView();
        if (view != null) {
            view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.REREGISTER_REGIONS);
            view.hide();
        }
    }

    @Override
    public void onNegativeButtonClick() {
        DialogView view = getView();
        if (view != null) {
            view.hide();
        }
    }
}
