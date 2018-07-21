package com.example.sergey.geofencingwithrealmapplication.Presenter.edit;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

public interface EditPointPresenter extends MVPPresenter<EditPointActivityView> {
    void onNavigationButtonClick();

    void onAddRegionButtonClick();

    void onEditRegionButtonClick();

    void onRemoveRegionButtonClick();
}
