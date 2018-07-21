package com.example.sergey.geofencingwithrealmapplication.View.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.R;

public class RemoveRegionDialog extends DialogFragment implements Dialog  {

    private static final String TAG = "remove_region_dialog";
    private static final String REGION_EXTRA = "region_extra";

    @NonNull
    public static Dialog getInstance(@NonNull Region region) {
        Bundle args = new Bundle();
        args.putParcelable(REGION_EXTRA, region);

        RemoveRegionDialog dialog = new RemoveRegionDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.remove_region_dialog_title)
                .setPositiveButton(R.string.remove_region_dialog_positive_button_text, (dialogInterface, i) -> {
                    // TODO: 21.07.18 Удаление
                })
                .setNegativeButton(R.string.remove_region_dialog_negative_button_text, null)
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
}
