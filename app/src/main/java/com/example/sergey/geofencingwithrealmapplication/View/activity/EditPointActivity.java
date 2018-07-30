package com.example.sergey.geofencingwithrealmapplication.View.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.example.sergey.geofencingwithrealmapplication.Presenter.edit.EditPointPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.edit.EditPointPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.adapter.RegionListAdapter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.OrderedRealmCollection;

public class EditPointActivity extends AppCompatActivity implements EditPointActivityView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitleView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.emptyText)
    TextView emptyRegionSListView;

    @BindView(R.id.regionsList)
    RecyclerView regionsList;

    @BindView(R.id.addRegionButton)
    FloatingActionButton addRegionButton;

    private RegionListAdapter regionListAdapter;

    private EditPointPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_points);

        ButterKnife.bind(this);

        initView();
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.detachView();
    }

    @OnClick(R.id.addRegionButton)
    void onAddRegionButtonClick() {
        presenter.onAddRegionButtonClick();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyListText() {
        emptyRegionSListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateRegionsList(@Nullable OrderedRealmCollection<Region> data) {
        regionListAdapter.updateData(data);
    }

    @Override
    public void showRegionsList() {
        regionsList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDialog(@NonNull DialogView dialog) {
        dialog.show(this);
    }

    @Override
    public void closeActivity() {
        onBackPressed();
    }

    @NonNull
    @Override
    public EditPointPresenter getPresenter() {
        return presenter;
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener((v) -> presenter.onNavigationButtonClick());
        toolbar.setContentInsetStartWithNavigation(0);
        toolbarTitleView.setText(R.string.activity_edit_points_toolbar_title);

        regionsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        regionListAdapter = new RegionListAdapter();
        regionsList.setAdapter(regionListAdapter);
    }

    private void initPresenter() {
        presenter = new EditPointPresenterImpl(RegionsDatabase.getInstance());
    }
}
