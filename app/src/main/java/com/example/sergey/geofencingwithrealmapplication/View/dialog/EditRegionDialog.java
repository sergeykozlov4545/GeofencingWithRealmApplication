package com.example.sergey.geofencingwithrealmapplication.View.dialog;

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
import com.example.sergey.geofencingwithrealmapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditRegionDialog extends DialogFragment implements Dialog {

    private static final String TAG = "edit_region_dialog";
    private static final String REGION_EXTRA = "region_extra";

    @BindView(R.id.nameView)
    TextInputEditText nameView;

    @BindView(R.id.latitudeView)
    TextInputEditText latitudeView;

    @BindView(R.id.longitudeView)
    TextInputEditText longitudeView;

    @BindView(R.id.radiusView)
    TextInputEditText radiusView;

    @NonNull
    public static Dialog getInstance(@NonNull Region region) {
        Bundle args = new Bundle();
        args.putParcelable(REGION_EXTRA, region);

        EditRegionDialog dialog = new EditRegionDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.edit_region_dialog_title)
                .setView(getDialogView())
                .setPositiveButton(R.string.edit_region_dialog_positive_button_text, (dialogInterface, i) -> {
                    // TODO: 21.07.18 Редактировать
                })
                .setNegativeButton(R.string.edit_region_dialog_negative_button_text, null)
                .create();
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

        Bundle args = getArguments();
        if (args != null) {
            Region region = args.getParcelable(REGION_EXTRA);

            nameView.setText(region.getName());
            latitudeView.setText(String.valueOf(region.getLatitude()));
            longitudeView.setText(String.valueOf(region.getLongitude()));
            radiusView.setText(String.valueOf(region.getRadius()));
        }

        return view;
    }
}
