package com.example.sergey.geofencingwithrealmapplication.Presenter.main;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Model.TrackPreference;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

public class MainActivityPresenterImpl
        extends BasePresenter<MainActivityView> implements MainActivityPresenter {

    @NonNull
    private TrackPreference trackPreference;

    public MainActivityPresenterImpl(@NonNull TrackPreference trackPreference) {
        this.trackPreference = trackPreference;
    }

    @Override
    public void viewIsReady() {
        MainActivityView view = getView();

        if (view != null) {
            view.updateTrackButton(trackPreference.getTrackState());
            view.updateLogData(LogEventDataBase.getInstance().getEvents());
        }
    }

    @Override
    public void onTrackButtonClicked() {
        MainActivityView view = getView();
        if (view == null) {
            return;
        }

        boolean newTrackZonesState = !trackPreference.getTrackState();

        if (RegionsDatabase.getInstance().getRegions().isEmpty()
                && newTrackZonesState) {
            view.showMessage(R.string.activity_main_track_location_empty_regions_list);
            return;
        }

        trackPreference.setTrackState(newTrackZonesState);
        view.updateTrackButton(newTrackZonesState);
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
