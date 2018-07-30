package com.example.sergey.geofencingwithrealmapplication.View.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEvent;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.example.sergey.geofencingwithrealmapplication.View.viewholder.LogListViewHolder;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class LogListAdapter extends RealmRecyclerViewAdapter<LogEvent, LogListViewHolder> {

    public LogListAdapter() {
        super(null, true);
    }

    @NonNull
    @Override
    public LogListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.actvity_main_list_item, viewGroup, false);
        return new LogListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogListViewHolder logListViewHolder, int position) {
        OrderedRealmCollection<LogEvent> data = getData();

        if (data != null && position < data.size()) {
            logListViewHolder.bindViewHolder(data.get(position));
        }
    }
}
