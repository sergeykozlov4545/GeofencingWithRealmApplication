package com.example.sergey.geofencingwithrealmapplication.View.dialog.RemoveRegionDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter.RemoveRegionDialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.RemoveRegionDialogPresenter.RemoveRegionDialogPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.CustomAlertDialogBuilder;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.RegionDialogImpl;

public class RemoveRegionDialog extends RegionDialogImpl {

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
                .setPositiveButton(R.string.remove_region_dialog_positive_button_text,
                        view -> presenter.onConfirmRemoveRegionButtonClick(regionId))
                .setNegativeButton(R.string.remove_region_dialog_negative_button_text,
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
