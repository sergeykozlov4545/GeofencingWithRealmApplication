package com.example.sergey.geofencingwithrealmapplication.View.dialog.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class DialogViewImpl extends DialogFragment implements DialogView {

    private static final String TAG = "dialog_view";

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
}
