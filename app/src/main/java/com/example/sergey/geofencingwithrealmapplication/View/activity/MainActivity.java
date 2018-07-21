package com.example.sergey.geofencingwithrealmapplication.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.example.sergey.geofencingwithrealmapplication.Presenter.main.MainActivityPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.startButton)
    Button startTrackButton;

    @BindView(R.id.stopButton)
    Button stopTrackButton;

    private MainActivityPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();

        presenter = new MainActivityPresenterImpl();
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

    @Override
    public void updateStartTrackButtonState(boolean state) {
        startTrackButton.setEnabled(state);
    }

    @Override
    public void updateStopTrackButtonState(boolean state) {
        stopTrackButton.setEnabled(state);
    }

    @Override
    public void showEditPointActivity() {
        startActivity(new Intent(MainActivity.this, EditPointActivity.class));
    }

    @OnClick(R.id.startButton)
    void onStartButtonClicked() {
        presenter.onStartButtonClicked();
    }

    @OnClick(R.id.stopButton)
    void onStopButtonClicked() {
        presenter.onStopButtonClicked();
    }

    private void initToolbar() {
        toolbar.inflateMenu(R.menu.activity_main_menu);
        toolbar.setOnMenuItemClickListener(menuItem -> {

            if (menuItem.getItemId() == R.id.editRegions) {
                presenter.onEditRegionsActionToolbarClicked();
            }

            return false;
        });
    }
}
