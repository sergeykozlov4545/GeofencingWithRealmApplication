package com.example.sergey.geofencingwithrealmapplication.View.dialog.RemoveRegionDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter.RemoveRegionDialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter.RemoveRegionDialogPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.CustomAlertDialogBuilder;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public class RemoveRegionDialog extends DialogFragment implements DialogView {

    private static final String TAG = "remove_region_dialog";
    private static final String REGION_ID_EXTRA = "region_id_extra";

    private String regionId;
    private RemoveRegionDialogPresenter presenter;

    @NonNull
    public static DialogView getInstance(@NonNull String regionId) {
        Bundle args = new Bundle();
        args.putString(REGION_ID_EXTRA, regionId);

        RemoveRegionDialog dialog = new RemoveRegionDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        regionId = (getArguments() != null) ? getArguments().getString(REGION_ID_EXTRA) : "";
        presenter = new RemoveRegionDialogPresenterImpl(RegionsDatabase.getInstance());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new CustomAlertDialogBuilder(getContext())
                .setTitle(R.string.remove_region_dialog_title)
                .setPositiveButton(R.string.remove_region_dialog_positive_button_text, view -> {
                    presenter.onConfirmRemoveRegionButtonClick(regionId);
                    dismiss();
                })
                .setNegativeButton(R.string.remove_region_dialog_negative_button_text, view -> dismiss())
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
}
