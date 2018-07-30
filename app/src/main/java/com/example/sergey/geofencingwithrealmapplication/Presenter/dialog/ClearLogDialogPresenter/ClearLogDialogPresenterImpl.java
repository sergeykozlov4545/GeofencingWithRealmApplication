package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.ClearLogDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.DialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public class ClearLogDialogPresenterImpl
        extends DialogPresenter<DialogView> implements ClearLogDialogPresenter {

    @NonNull
    private LogEventDataBase dataBase;

    public ClearLogDialogPresenterImpl(@NonNull LogEventDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void onConfirmClearLogButtonClick() {
        dataBase.removeAllEvents();
        hideDialog();
    }

    @Override
    public void onNegativeButtonClick() {
        hideDialog();
    }
}
