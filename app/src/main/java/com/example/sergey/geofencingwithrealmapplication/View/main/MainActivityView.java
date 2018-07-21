package com.example.sergey.geofencingwithrealmapplication.View.main;

import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

public interface MainActivityView extends MVPView {
    void updateStartTrackButtonState(boolean state);

    void updateStopTrackButtonState(boolean state);

    void showEditPointActivity();
}
