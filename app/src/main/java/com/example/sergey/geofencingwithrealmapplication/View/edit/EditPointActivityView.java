package com.example.sergey.geofencingwithrealmapplication.View.edit;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.Dialog;

public interface EditPointActivityView extends MVPView {
    void showProgress();

    void hideProgress();

    void showEmptyListText();

    void showRegionsList();

    void showDialog(@NonNull Dialog dialog);

    void closeActivity();
}
