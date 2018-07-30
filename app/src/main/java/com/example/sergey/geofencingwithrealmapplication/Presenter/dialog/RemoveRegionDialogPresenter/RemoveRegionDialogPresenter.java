package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.RegionDialog;

public interface RemoveRegionDialogPresenter extends MVPPresenter<RegionDialog> {
    void onConfirmRemoveRegionButtonClick(@NonNull String regionId);

    void onNegativeButtonClick();
}
