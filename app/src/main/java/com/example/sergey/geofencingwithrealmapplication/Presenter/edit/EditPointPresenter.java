package com.example.sergey.geofencingwithrealmapplication.Presenter.edit;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

public interface EditPointPresenter extends MVPPresenter<EditPointActivityView> {
    void onNavigationButtonClick();

    void onAddRegionButtonClick();

    void onEditRegionButtonClick();

    void onRemoveRegionButtonClick(@NonNull Region region);

    void onCreateRegion(@NonNull String name, double latitude, double longitude, int radius);

    void onRemoveRegion(@NonNull Region region);
}
