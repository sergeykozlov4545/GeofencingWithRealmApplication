package com.example.sergey.geofencingwithrealmapplication.View.dialog.ClearLogDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.ClearLogDialogPresenter.ClearLogDialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.ClearLogDialogPresenter.ClearLogDialogPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.CustomAlertDialogBuilder;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogViewImpl;

public class ClearLogDialog extends DialogViewImpl {

    private ClearLogDialogPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ClearLogDialogPresenterImpl(LogEventDataBase.getInstance());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new CustomAlertDialogBuilder(getContext())
                .setTitle(R.string.clear_log_dialog_title)
                .setPositiveButton(R.string.clear_log_dialog_positive_button_text,
                        view -> presenter.onConfirmClearLogButtonClick())
                .setNegativeButton(R.string.clear_log_dialog_negative_button_text,
                        view -> presenter.onNegativeButtonClick())
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }
}
