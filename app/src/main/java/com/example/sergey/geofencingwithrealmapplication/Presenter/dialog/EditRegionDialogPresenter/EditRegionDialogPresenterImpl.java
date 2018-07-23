package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.EditRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog.EditRegionDialogView;

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
    public void onConfirmEditRegionButtonClick(@NonNull String regionId, @NonNull String name, double latitude, double longitude, int radius) {
        regionsDatabase.updateRegion(regionId, name, latitude, longitude, radius);
    }
}
