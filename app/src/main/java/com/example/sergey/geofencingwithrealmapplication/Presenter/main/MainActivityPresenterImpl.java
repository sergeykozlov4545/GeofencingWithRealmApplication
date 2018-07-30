package com.example.sergey.geofencingwithrealmapplication.Presenter.main;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.R;
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
            view.updateTrackButton();
            view.updateLogData(LogEventDataBase.getInstance().getEvents());
        }
    }

    @Override
    public void onTrackButtonClicked(boolean trackZones) {
        MainActivityView view = getView();
        if (view == null) {
            return;
        }

        boolean newTrackZonesState = !trackZones;

        if (RegionsDatabase.getInstance().getRegions().isEmpty()
                && newTrackZonesState) {
            view.showMessage(R.string.activity_main_track_location_empty_regions_list);
            return;
        }

        view.updateTrackState(newTrackZonesState);
        view.updateTrackButton();

        view.showMessage(newTrackZonesState
                ? R.string.activity_main_track_location_on
                : R.string.activity_main_track_location_off);
        view.sendGeofenceServiceEvent(newTrackZonesState
                ? GeofenceService.TypeOperation.REGISTER_ALL_REGIONS
                : GeofenceService.TypeOperation.UNREGISTER_ALL_REGIONS);
    }

    @Override
    public void onClearLogsActionToolbarClicked() {
        MainActivityView view = getView();
        if (view != null) {
            view.showClearLogDialog();
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
