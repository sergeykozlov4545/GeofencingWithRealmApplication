package com.example.sergey.geofencingwithrealmapplication.Presenter.main;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

public class MainActivityPresenterImpl
        extends BasePresenter<MainActivityView> implements MainActivityPresenter {

    @Override
    public void viewIsReady() {
        // TODO: 21.07.18 Получаем список зарегистрированных зон и смотрим на их размер
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(true);
            view.updateStopTrackButtonState(false);
        }
    }

    @Override
    public void onStartButtonClicked() {
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(false);
            view.updateStopTrackButtonState(true);
        }
        // TODO: 21.07.18 Регистрируем список зон
        // TODO: 23.07.18 Добавляем их в базу
    }

    @Override
    public void onStopButtonClicked() {
        MainActivityView view = getView();

        if (view != null) {
            view.updateStartTrackButtonState(true);
            view.updateStopTrackButtonState(false);
        }

        // TODO: 21.07.18 Отменяем регистрацию зон
        // TODO: 23.07.18 Чистим базу от них
    }

    @Override
    public void onEditRegionsActionToolbarClicked() {
        MainActivityView view = getView();
        if (view != null) {
            view.showEditPointActivity();
        }
    }
}
