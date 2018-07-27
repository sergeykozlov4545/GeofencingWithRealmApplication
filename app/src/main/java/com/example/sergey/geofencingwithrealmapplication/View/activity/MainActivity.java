package com.example.sergey.geofencingwithrealmapplication.View.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.example.sergey.geofencingwithrealmapplication.Presenter.main.MainActivityPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.main.MainActivityPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final int PERMISSION_REQUEST_CODE = 100;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.trackButton)
    Button trackButton;

    private MainActivityPresenter presenter;
    private boolean trackGeozones;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !checkAccessFineLocationPermission()) {
            requestAccessFineLocationPermission();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.detachView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length == 1
                    && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults.length == 1
                    && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
    }

    @Override
    public void updateTrackState(boolean trackZones) {
        this.trackGeozones = trackZones;
    }

    @Override
    public void updateTrackButtonText() {
        trackButton.setText(!trackGeozones
                ? R.string.activity_main_start_button_text
                : R.string.activity_main_stop_button_text);
    }

    @Override
    public void sendGeofenceServiceEvent(@NonNull GeofenceService.TypeOperation typeOperation) {
        Intent intent = new Intent(this, GeofenceService.class)
                .putExtra(GeofenceService.TYPE_OPERATION_EXTRA, typeOperation);
        startService(intent);
    }

    @Override
    public void showEditPointActivity() {
        startActivity(new Intent(MainActivity.this, EditPointActivity.class));
    }

    @OnClick(R.id.trackButton)
    void onTrackButtonClicked() {
        presenter.onTrackButtonClicked(trackGeozones);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkAccessFineLocationPermission() {
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestAccessFineLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }
}
