package com.example.sergey.geofencingwithrealmapplication.Presenter.base;

import android.support.annotation.Nullable;

import com.example.sergey.geofencingwithrealmapplication.View.base.MVPView;

public abstract class BasePresenter<V extends MVPView> implements MVPPresenter<V> {

    @Nullable
    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Nullable
    @Override
    public V getView() {
        return view;
    }
}
