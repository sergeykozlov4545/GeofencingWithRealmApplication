package com.example.sergey.geofencingwithrealmapplication.View.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sergey.geofencingwithrealmapplication.Presenter.edit.EditPointPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.edit.EditPointPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPointActivity extends AppCompatActivity implements EditPointActivityView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.emptyText)
    TextView emptyRegionSListView;

    @BindView(R.id.regionsList)
    RecyclerView regionsList;

    @BindView(R.id.addRegionButton)
    FloatingActionButton addRegionButton;

    private EditPointPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_points);

        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener((v) -> presenter.onNavigationButtonClick());
        toolbar.setContentInsetStartWithNavigation(0);

        presenter = new EditPointPresenterImpl();
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
    public void showRegionsList() {
        regionsList.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeActivity() {
        onBackPressed();
    }
}
