package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.AddRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public interface AddRegionDialogPresenter extends MVPPresenter<DialogView> {
    void onConfirmAddRegionButtonClick(@NonNull String name, double latitude, double longitude, int radius);
}
