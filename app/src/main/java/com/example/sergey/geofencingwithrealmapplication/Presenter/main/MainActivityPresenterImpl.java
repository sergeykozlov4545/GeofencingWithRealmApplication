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
            view.updateTrackState(!registeredRegions.isEmpty());
            view.updateTrackButtonText();
        }
    }

    @Override
    public void onTrackButtonClicked(boolean trackZones) {
        boolean newTrackZonesState = !trackZones;

        MainActivityView view = getView();

        if (view != null) {
            // TODO: 27.07.18 Нужно делать проверку на существование хотябы одной зоны
            view.updateTrackState(newTrackZonesState);
            view.updateTrackButtonText();

            if (newTrackZonesState) {
                view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.REGISTER_ALL_REGIONS);
            } else {
                view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.UNREGISTER_ALL_REGIONS);
            }
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
