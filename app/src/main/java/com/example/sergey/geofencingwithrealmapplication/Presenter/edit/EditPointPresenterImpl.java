package com.example.sergey.geofencingwithrealmapplication.Presenter.edit;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

public class EditPointPresenterImpl
        extends BasePresenter<EditPointActivityView> implements EditPointPresenter {

    @Override
    public void viewIsReady() {

    }

    @Override
    public void onNavigationButtonClick() {
        EditPointActivityView view = getView();

        if (view != null) {
            view.closeActivity();
        }
    }

    @Override
    public void onAddRegionButtonClick() {

    }

    @Override
    public void onEditRegionButtonClick() {

    }

    @Override
    public void onRemoveRegionButtonClick() {

    }
}
