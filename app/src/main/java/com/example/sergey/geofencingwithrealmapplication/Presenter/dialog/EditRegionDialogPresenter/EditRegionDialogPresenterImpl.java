package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.EditRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog.EditRegionDialogView;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;
import com.google.android.gms.maps.model.LatLng;

public class EditRegionDialogPresenterImpl
        extends BasePresenter<EditRegionDialogView> implements EditRegionDialogPresenter {

    @NonNull
    private RegionsDatabase regionsDatabase;

    public EditRegionDialogPresenterImpl(@NonNull RegionsDatabase regionsDatabase) {
        this.regionsDatabase = regionsDatabase;
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void onDialogShowed(@NonNull String regionId) {
        EditRegionDialogView view = getView();
        Region region = regionsDatabase.getRegion(regionId);

        if (view != null && region != null) {
            view.updateView(region);
        }
    }

    @Override
    public void onConfirmEditRegionButtonClick(@NonNull String regionId,
                                               @NonNull String name,
                                               @NonNull LatLng center,
                                               int radius) {
        regionsDatabase.updateRegion(regionId, name, center, radius);
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
