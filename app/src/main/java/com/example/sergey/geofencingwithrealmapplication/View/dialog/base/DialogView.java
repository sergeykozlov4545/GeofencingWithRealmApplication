package com.example.sergey.geofencingwithrealmapplication.View.dialog.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

public interface DialogView extends MVPView {
    void show(@NonNull Context context);

    void hide();
}
