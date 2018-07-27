package com.example.sergey.geofencingwithrealmapplication.Presenter.main;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

public interface MainActivityPresenter extends MVPPresenter<MainActivityView> {
    void onTrackButtonClicked(boolean trackZones);

    void onClearLogsActionToolbarClicked();

    void onEditRegionsActionToolbarClicked();
}
