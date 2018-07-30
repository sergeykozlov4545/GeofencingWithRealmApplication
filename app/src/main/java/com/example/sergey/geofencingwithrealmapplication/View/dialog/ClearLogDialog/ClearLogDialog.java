package com.example.sergey.geofencingwithrealmapplication.View.dialog.ClearLogDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEventDataBase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.ClearLogDialogPresenter.ClearLogDialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.ClearLogDialogPresenter.ClearLogDialogPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.CustomAlertDialogBuilder;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public class ClearLogDialog extends DialogFragment implements DialogView {

    private static final String TAG = "clear_log_dialog";

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

    @Override
    public void show(@NonNull Context context) {
        if (!(context instanceof FragmentActivity)) {
            throw new RuntimeException("context doesn't implements FragmentActivity");
        }

        setCancelable(false);
        show(((FragmentActivity) context).getSupportFragmentManager(), TAG);
    }

    @Override
    public void hide() {
        dismiss();
    }

    @Override
    public void sendGeofenceServiceEvent(@NonNull GeofenceService.TypeOperation typeOperation) {
        Intent intent = new Intent(getContext(), GeofenceService.class)
                .putExtra(GeofenceService.TYPE_OPERATION_EXTRA, typeOperation);
        getContext().startService(intent);
    }
}
