package com.example.sergey.geofencingwithrealmapplication.Presenter.main;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

import java.util.List;

public class MainActivityPresenterImpl
        extends BasePresenter<MainActivityView> implements MainActivityPresenter {

    @Override
    public void viewIsReady() {
        List<Region> registeredRegions = RegionsDatabase.getInstance().getRegisteredRegions();

        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(registeredRegions.isEmpty());
            view.updateStopTrackButtonState(!registeredRegions.isEmpty());
        }
    }

    @Override
    public void onStartButtonClicked() {
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(false);
            view.updateStopTrackButtonState(true);
            view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.REGISTER_ALL_REGIONS);
        }
    }

    @Override
    public void onStopButtonClicked() {
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(true);
            view.updateStopTrackButtonState(false);
            view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.UNREGISTER_ALL_REGIONS);
        }
    }

    @Override
    public void onEditRegionsActionToolbarClicked() {
        MainActivityView view = getView();
        if (view != null) {
            view.showEditPointActivity();
        }
    }
}
