package com.example.sergey.geofencingwithrealmapplication.View.edit;

import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

public interface EditPointActivityView extends MVPView {
    void showProgress();

    void hideProgress();

    void showEmptyListText();

    void showRegionsList();

    void closeActivity();
}
