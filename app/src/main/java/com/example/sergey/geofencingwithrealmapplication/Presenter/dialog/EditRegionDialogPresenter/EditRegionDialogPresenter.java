package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.EditRegionDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog.EditRegionDialogView;

public interface EditRegionDialogPresenter extends MVPPresenter<EditRegionDialogView> {
    void onDialogShowed(@NonNull String regionId);

    // TODO: 23.07.18 Подумать насчет аргументов. Может Добавить новый класс
    void onConfirmEditRegionButtonClick(@NonNull String regionId, @NonNull String name, double latitude, double longitude, int radius);
}
