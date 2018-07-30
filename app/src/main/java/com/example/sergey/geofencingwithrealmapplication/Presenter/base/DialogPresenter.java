package com.example.sergey.geofencingwithrealmapplication.Presenter.base;

import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public class DialogPresenter<V extends DialogView> extends BasePresenter<V> {

    @Override
    public void viewIsReady() {

    }

    protected void hideDialog() {
        V view = getView();
        if (view != null) {
            view.hide();
        }
    }
}
