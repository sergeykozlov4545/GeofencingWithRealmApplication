package com.example.sergey.geofencingwithrealmapplication.Presenter.edit;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.AddRegionDialog;
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
        EditPointActivityView view = getView();
        if (view != null) {
            view.showDialog(new AddRegionDialog());
        }
    }

    @Override
    public void onEditRegionButtonClick() {
        EditPointActivityView view = getView();
        if (view != null) {
//            view.showDialog(EditRegionDialog.getInstance());
        }
    }

    @Override
    public void onRemoveRegionButtonClick() {

    }
}
