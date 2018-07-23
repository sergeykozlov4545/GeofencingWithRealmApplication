package com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.EditRegionDialogPresenter.EditRegionDialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.EditRegionDialogPresenter.EditRegionDialogPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditRegionDialog extends DialogFragment implements EditRegionDialogView {

    private static final String TAG = "edit_region_dialog";
    private static final String REGION_ID_EXTRA = "region_id_extra";

    @BindView(R.id.nameView)
    TextInputEditText nameView;

    @BindView(R.id.latitudeView)
    TextInputEditText latitudeView;

    @BindView(R.id.longitudeView)
    TextInputEditText longitudeView;

    @BindView(R.id.radiusView)
    TextInputEditText radiusView;

    private String regionId;
    private EditRegionDialogPresenter presenter;

    @NonNull
    public static DialogView getInstance(@NonNull String regionId) {
        Bundle args = new Bundle();
        args.putString(REGION_ID_EXTRA, regionId);

        EditRegionDialog dialog = new EditRegionDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        regionId = (getArguments() == null) ? "" : getArguments().getString(REGION_ID_EXTRA);
        presenter = new EditRegionDialogPresenterImpl(RegionsDatabase.getInstance());
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.edit_region_dialog_title)
                .setView(getDialogView())
                .setPositiveButton(R.string.edit_region_dialog_positive_button_text, (dialogInterface, i) -> {
                    // TODO: 23.07.18 Сделать проверку на пустоту. Snackbar

                    String name = nameView.getText().toString();
                    double latitude = Double.parseDouble(latitudeView.getText().toString());
                    double longitude = Double.parseDouble(longitudeView.getText().toString());
                    int radius = Integer.parseInt(radiusView.getText().toString());

                    presenter.onConfirmEditRegionButtonClick(regionId, name, latitude, longitude, radius);
                })
                .setNegativeButton(R.string.edit_region_dialog_negative_button_text, null)
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.viewIsReady();
        presenter.onDialogShowed(regionId);
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
    public void updateView(@NonNull Region region) {
        nameView.setText(region.getName());
        latitudeView.setText(String.valueOf(region.getLatitude()));
        longitudeView.setText(String.valueOf(region.getLongitude()));
        radiusView.setText(String.valueOf(region.getRadius()));
    }

    private View getDialogView() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_add_region, null);

        ButterKnife.bind(this, view);

        return view;
    }
}