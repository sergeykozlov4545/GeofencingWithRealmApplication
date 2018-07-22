package com.example.sergey.geofencingwithrealmapplication.Presenter.edit;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.AddRegionDialog;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.RemoveRegionDialog;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

import io.realm.OrderedRealmCollection;

public class EditPointPresenterImpl
        extends BasePresenter<EditPointActivityView> implements EditPointPresenter {

    @NonNull
    private RegionsDatabase regionsDatabase;

    public EditPointPresenterImpl(@NonNull RegionsDatabase regionsDatabase) {
        this.regionsDatabase = regionsDatabase;
    }

    @Override
    public void viewIsReady() {
        EditPointActivityView view = getView();

        if (view != null) {
            view.showProgress();
        }

        regionsDatabase.getRegions(new RegionsDatabase.LoadRegionsCallback() {
            @Override
            public void dataIsLoaded(@NonNull OrderedRealmCollection<Region> data) {
                EditPointActivityView view = getView();

                if (view != null) {
                    view.hideProgress();
                    view.updateRegionsList(data);
                    view.showRegionsList();
                }
            }

            @Override
            public void dataIsEmpty() {
                EditPointActivityView view = getView();

                if (view != null) {
                    view.hideProgress();
                    view.showEmptyListText();
                }
            }
        });
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
            // TODO: 21.07.18 Незабыть раскоментить
//            view.showDialog(EditRegionDialog.getInstance());
        }
    }

    @Override
    public void onRemoveRegionButtonClick(@NonNull Region region) {
        EditPointActivityView view = getView();
        if (view != null) {
            view.showDialog(RemoveRegionDialog.getInstance(region));
        }
    }

    @Override
    public void onCreateRegion(@NonNull String name, double latitude, double longitude, int radius) {
        regionsDatabase.addRegion(name, latitude, longitude, radius);
    }

    @Override
    public void onRemoveRegion(@NonNull Region region) {
        regionsDatabase.removeRegion(region);
    }
}
