package com.example.sergey.geofencingwithrealmapplication.View.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.edit.EditPointActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegionListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nameRegionView)
    TextView nameRegionView;

    @BindView(R.id.positionRegionView)
    TextView positionRegionView;

    @BindView(R.id.radiusRegionView)
    TextView radiusRegionView;

    @BindView(R.id.editRegionButton)
    ImageView editRegionButton;

    @BindView(R.id.removeRegionButton)
    ImageView removeRegionButton;

    @NonNull
    private Region region;

    public RegionListViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bindViewHolder(@NonNull Region region) {
        this.region = region;
        nameRegionView.setText(region.getName());
        positionRegionView.setText(String.format("%s, %s", region.getLatitude(), region.getLongitude()));
        radiusRegionView.setText(String.valueOf(region.getRadius()));
    }

    @OnClick(R.id.removeRegionButton)
    public void onRemoveRegionButtonClick() {
        Context context = itemView.getContext();
        if (!(context instanceof EditPointActivityView)) {
            throw new RuntimeException("context doesn't implements EditPointActivityView");
        }
        ((EditPointActivityView) context).getPresenter().onRemoveRegionButtonClick(region);
    }
}