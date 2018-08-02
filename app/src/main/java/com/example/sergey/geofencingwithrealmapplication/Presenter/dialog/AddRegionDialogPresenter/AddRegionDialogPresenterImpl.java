package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.AddRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RealmLatLng;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.DialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceBroadcast;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.RegionDialog;

public class AddRegionDialogPresenterImpl
        extends DialogPresenter<RegionDialog> implements AddRegionDialogPresenter {

    @NonNull
    private RegionsDatabase regionsDatabase;

    public AddRegionDialogPresenterImpl(@NonNull RegionsDatabase regionsDatabase) {
        this.regionsDatabase = regionsDatabase;
    }

    @Override
    public void onConfirmAddRegionButtonClick(@NonNull String name,
                                              @NonNull RealmLatLng center,
                                              int radius) {
        regionsDatabase.addRegion(name, center, radius);
        RegionDialog view = getView();
        if (view != null) {
            view.sendGeofenceBroadcastEvent(GeofenceBroadcast.TypeOperation.REREGISTER_REGIONS);
        }
        hideDialog();
    }

    @Override
    public void onNegativeButtonClick() {
        hideDialog();
    }
}
