package com.example.sergey.geofencingwithrealmapplication.View.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sergey.geofencingwithrealmapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPointActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_points);

        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener((v) -> onBackPressed());
        toolbar.setContentInsetStartWithNavigation(0);
    }

    @OnClick(R.id.addRegionButton)
    void onAddRegionButtonClick() {

    }
}
