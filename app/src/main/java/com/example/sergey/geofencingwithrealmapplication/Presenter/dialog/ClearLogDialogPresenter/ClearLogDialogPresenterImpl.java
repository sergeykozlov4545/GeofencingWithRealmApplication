package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.ClearLogDialogPresenter;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public class ClearLogDialogPresenterImpl extends BasePresenter<DialogView> implements ClearLogDialogPresenter {

    @NonNull
    private LogEventDataBase dataBase;

    public ClearLogDialogPresenterImpl(@NonNull LogEventDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void viewIsReady() {

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

    private void hideDialog() {
        DialogView view = getView();
        if (view != null) {
            view.hide();
        }
    }
}
