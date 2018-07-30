package com.example.sergey.geofencingwithrealmapplication.View.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Presenter.edit.EditPointPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

import io.realm.OrderedRealmCollection;

public interface EditPointActivityView extends MVPView {
    void showProgress();

    void hideProgress();

    void showEmptyListText();

    void updateRegionsList(@Nullable OrderedRealmCollection<Region> data);

    void showRegionsList();

    void showDialog(@NonNull DialogView dialog);

    void closeActivity();

    @NonNull
    EditPointPresenter getPresenter();
}
