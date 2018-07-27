package com.example.sergey.geofencingwithrealmapplication.View.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.viewholder.RegionListViewHolder;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RegionListAdapter extends RealmRecyclerViewAdapter<Region, RegionListViewHolder> {

    public RegionListAdapter() {
        super(null, true);
    }

    @NonNull
    @Override
    public RegionListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_edit_points_list_item, viewGroup, false);
        return new RegionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionListViewHolder regionListViewHolder, int position) {
        OrderedRealmCollection<Region> data = getData();

        if (data != null && position < data.size()) {
            regionListViewHolder.bindViewHolder(data.get(position));
        }
    }
}
