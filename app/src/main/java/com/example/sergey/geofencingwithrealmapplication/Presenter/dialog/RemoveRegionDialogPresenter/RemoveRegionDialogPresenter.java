package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public interface RemoveRegionDialogPresenter extends MVPPresenter<DialogView> {
    void onConfirmRemoveRegionButtonClick(@NonNull String regionId);
}
