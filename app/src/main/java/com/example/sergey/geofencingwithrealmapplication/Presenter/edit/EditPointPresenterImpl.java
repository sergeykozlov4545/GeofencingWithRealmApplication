package com.example.sergey.geofencingwithrealmapplication.Presenter.edit;

import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.RealmLatLng;
import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.base.BasePresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.AddRegionDialog.AddRegionDialog;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.EditRegionDialog.EditRegionDialog;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.RemoveRegionDialog.RemoveRegionDialog;
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

        if (view == null) {
            return;
        }

        if (regionsDatabase.getRegions().isEmpty()) {
            regionsDatabase.addRegion("Технология", new RealmLatLng(55.420859, 65.273978), 400);
            regionsDatabase.addRegion("Центральный вокзал", new RealmLatLng(55.441445, 65.319457), 400);
            regionsDatabase.addRegion("Некрасово", new RealmLatLng(55.452881, 65.340784), 400);
            regionsDatabase.addRegion("Рио", new RealmLatLng(55.428296, 65.299177), 400);
            regionsDatabase.addRegion("Горсад", new RealmLatLng(55.439788, 65.344471), 400);
            regionsDatabase.addRegion("Кольцо у гаражей", new RealmLatLng(55.451240, 65.372292), 400);
            regionsDatabase.addRegion("ЦПКиО", new RealmLatLng(55.430798, 65.326772), 400);
            regionsDatabase.addRegion("Кировский пляж", new RealmLatLng(55.430760, 65.349454), 400);
            regionsDatabase.addRegion("Молодежный", new RealmLatLng(55.439380, 65.364141), 400);
        }

        OrderedRealmCollection<Region> regions = regionsDatabase.getRegions();

        if (regions.isEmpty()) {
            view.showEmptyListText();
            return;
        }

        view.updateRegionsList(regions);
        view.showRegionsList();
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
    public void onEditRegionButtonClick(@NonNull String regionId) {
        EditPointActivityView view = getView();
        if (view != null) {
            view.showDialog(EditRegionDialog.getInstance(regionId));
        }
    }

    @Override
    public void onRemoveRegionButtonClick(@NonNull String regionId) {
        EditPointActivityView view = getView();
        if (view != null) {
            view.showDialog(RemoveRegionDialog.getInstance(regionId));
        }
    }
}
