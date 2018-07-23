package com.example.sergey.geofencingwithrealmapplication.View.dialog.AddRegionDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.AddRegionDialogPresenter.AddRegionDialogPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.AddRegionDialogPresenter.AddRegionDialogPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.CustomAlertDialogBuilder;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddRegionDialog extends DialogFragment implements DialogView {

    private static final String TAG = "add_region_dialog";

    @BindView(R.id.content)
    View contentView;

    @BindView(R.id.nameView)
    TextInputEditText nameView;

    @BindView(R.id.latitudeView)
    TextInputEditText latitudeView;

    @BindView(R.id.longitudeView)
    TextInputEditText longitudeView;

    @BindView(R.id.radiusView)
    TextInputEditText radiusView;

    private AddRegionDialogPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddRegionDialogPresenterImpl(RegionsDatabase.getInstance());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new CustomAlertDialogBuilder(getContext())
                .setTitle(R.string.add_region_dialog_title)
                .setView(getDialogView())
                .setPositiveButton(R.string.add_region_dialog_positive_button_text, v -> {
                    if (TextUtils.isEmpty(nameView.getText())
                            || TextUtils.isEmpty(latitudeView.getText())
                            || TextUtils.isEmpty(longitudeView.getText())
                            || TextUtils.isEmpty(radiusView.getText())) {
                        Snackbar.make(contentView, R.string.region_dialog_error_message_text, Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    String regionName = nameView.getText().toString();
                    double latitude = Double.parseDouble(latitudeView.getText().toString());
                    double longitude = Double.parseDouble(longitudeView.getText().toString());
                    int radius = Integer.parseInt(radiusView.getText().toString());

                    presenter.onConfirmAddRegionButtonClick(regionName, latitude, longitude, radius);
                    dismiss();
                })
                .setNegativeButton(R.string.add_region_dialog_negative_button_text, view -> dismiss())
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

    private View getDialogView() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_add_region, null);

        ButterKnife.bind(this, view);

        return view;
    }
}
