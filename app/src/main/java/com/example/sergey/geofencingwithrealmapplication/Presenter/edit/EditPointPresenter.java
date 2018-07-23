package com.example.sergey.geofencingwithrealmapplication.Presenter.edit;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

public interface EditPointPresenter extends MVPPresenter<EditPointActivityView> {
    void onNavigationButtonClick();

    void onAddRegionButtonClick();

    void onEditRegionButtonClick(@NonNull String regionId);

    void onRemoveRegionButtonClick(@NonNull String regionId);
}
