package com.example.sergey.geofencingwithrealmapplication.View.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEvent;
import com.example.sergey.geofencingwithrealmapplication.Presenter.main.MainActivityPresenter;
import com.example.sergey.geofencingwithrealmapplication.Presenter.main.MainActivityPresenterImpl;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.Service.GeofenceService;
import com.example.sergey.geofencingwithrealmapplication.View.adapter.LogListAdapter;
import com.example.sergey.geofencingwithrealmapplication.View.main.MainActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.OrderedRealmCollection;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final int PERMISSION_REQUEST_CODE = 100;

    @BindView(R.id.content)
    View contentView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitleView;

    @BindView(R.id.logList)
    RecyclerView logList;

    @BindView(R.id.trackButton)
    FloatingActionButton trackButton;

    private MainActivityPresenter presenter;
    private boolean trackGeozones;
    private LogListAdapter logListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();
        initPresenter();
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
    public void updateTrackButton() {
        trackButton.setImageResource(trackGeozones
                ? R.drawable.ic_track_location_on_white_24dp
                : R.drawable.ic_track_location_off_white_24dp);
    }

    @Override
    public void showEnabledTrackLocationMessage() {
        Snackbar.make(contentView, R.string.activity_main_track_location_on, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDisabledTrackLocationMessage() {
        Snackbar.make(contentView, R.string.activity_main_track_location_off, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void updateLogData(@NonNull OrderedRealmCollection<LogEvent> logEvents) {
        logListAdapter.updateData(logEvents);
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

    private void initView() {
        toolbar.inflateMenu(R.menu.activity_main_menu);
        toolbar.setOnMenuItemClickListener(menuItem -> {

            if (menuItem.getItemId() == R.id.editRegions) {
                presenter.onEditRegionsActionToolbarClicked();
            }

            if (menuItem.getItemId() == R.id.clearLogs) {
                presenter.onClearLogsActionToolbarClicked();
            }

            return false;
        });
        toolbarTitleView.setText(R.string.activity_main_toolbar_title);

        logList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        logListAdapter = new LogListAdapter();
        logList.setAdapter(logListAdapter);
    }

    private void initPresenter() {
        presenter = new MainActivityPresenterImpl();
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
