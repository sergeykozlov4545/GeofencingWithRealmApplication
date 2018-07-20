package com.example.sergey.geofencingwithrealmapplication.View.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.example.sergey.geofencingwithrealmapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.startButton)
    Button startTrackButton;

    @BindView(R.id.stopButton)
    Button stopTrackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        initToolbar();
    }

    @OnClick(R.id.startButton)
    void onStartButtonClicked() {

    }

    @OnClick(R.id.stopButton)
    void onStopButtonClicked() {

    }

    private void initToolbar() {
        toolbar.inflateMenu(R.menu.activity_main_menu);
        toolbar.setOnMenuItemClickListener(menuItem -> {

            if (menuItem.getItemId() == R.id.editRegions) {
                startActivity(new Intent(MainActivity.this, EditPointActivity.class));
            }

            return false;
        });
    }
}
