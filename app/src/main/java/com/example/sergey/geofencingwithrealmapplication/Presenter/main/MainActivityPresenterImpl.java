package com.example.sergey.geofencingwithrealmapplication.Presenter.main;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
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
            view.updateTrackButton();
            view.updateLogData(LogEventDataBase.getInstance().getEvents());
        }
    }

    @Override
    public void onTrackButtonClicked(boolean trackZones) {
        boolean newTrackZonesState = !trackZones;

        MainActivityView view = getView();

        if (view != null) {
            // TODO: 27.07.18 Нужно делать проверку на существование хотябы одной зоны
            view.updateTrackState(newTrackZonesState);
            view.updateTrackButton();

            if (newTrackZonesState) {
                view.showEnabledTrackLocationMessage();
                view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.REGISTER_ALL_REGIONS);
            } else {
                view.showDisabledTrackLocationMessage();
                view.sendGeofenceServiceEvent(GeofenceService.TypeOperation.UNREGISTER_ALL_REGIONS);
            }
        }
    }

    @Override
    public void onClearLogsActionToolbarClicked() {
        // TODO: 27.07.18 Удаление через диалог
        LogEventDataBase.getInstance().removeAllEvents();
    }

    @Override
    public void onEditRegionsActionToolbarClicked() {
        MainActivityView view = getView();
        if (view != null) {
            view.showEditPointActivity();
        }
    }
}
