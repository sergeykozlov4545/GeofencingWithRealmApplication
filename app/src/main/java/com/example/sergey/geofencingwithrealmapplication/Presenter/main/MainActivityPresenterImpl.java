package com.example.sergey.geofencingwithrealmapplication.Presenter.main;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

public class MainActivityPresenterImpl
        extends BasePresenter<MainActivityView> implements MainActivityPresenter {

    @Override
    public void viewIsReady() {
        // TODO: 21.07.18 Как-то узнаем, есть сейчас слежка или нет
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(true);
            view.updateStopTrackButtonState(false);
        }
    }

    @Override
    public void onStartButtonClicked() {
        // TODO: 21.07.18 Чето делаем
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(false);
            view.updateStopTrackButtonState(true);
        }
    }

    @Override
    public void onStopButtonClicked() {
        // TODO: 21.07.18 Чето делаем
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(true);
            view.updateStopTrackButtonState(false);
        }
    }

    @Override
    public void onEditRegionsActionToolbarClicked() {
        MainActivityView view = getView();
        if (view != null) {
            view.showEditPointActivity();
        }
    }
}
