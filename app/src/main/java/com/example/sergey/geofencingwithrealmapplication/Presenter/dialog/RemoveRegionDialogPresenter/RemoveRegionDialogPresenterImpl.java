package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.DialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.RegionDialog;

public class RemoveRegionDialogPresenterImpl
        extends DialogPresenter<RegionDialog> implements RemoveRegionDialogPresenter {

    @NonNull
    private RegionsDatabase regionsDatabase;

    public RemoveRegionDialogPresenterImpl(@NonNull RegionsDatabase regionsDatabase) {
        this.regionsDatabase = regionsDatabase;
    }

    @Override
    public void onConfirmRemoveRegionButtonClick(@NonNull String regionId) {
        regionsDatabase.removeRegion(regionId);
        RegionDialog view = getView();
        if (view != null) {
            view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.REREGISTER_REGIONS);
        }
        hideDialog();
    }

    @Override
    public void onNegativeButtonClick() {
        hideDialog();
    }
}
