package com.example.sergey.geofencingwithrealmapplication.Presenter.base;

import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

public interface MVPPresenter<V extends MVPView> {
    void attachView(V view);

    void detachView();

    void viewIsReady();

    @Nullable
    V getView();
}
