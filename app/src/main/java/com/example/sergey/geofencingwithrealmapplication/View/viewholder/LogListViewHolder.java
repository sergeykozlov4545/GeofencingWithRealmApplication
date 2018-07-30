package com.example.sergey.geofencingwithrealmapplication.View.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEvent;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.google.android.gms.location.Geofence;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class LogListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.eventView)
    TextView eventView;

    @BindView(R.id.regionNameView)
    TextView regionNameView;

    @BindView(R.id.registeredRegionsView)
    TextView registeredRegionsView;

    public LogListViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bindViewHolder(@NonNull LogEvent logEvent) {
        if (logEvent.getTransitionType() == Geofence.GEOFENCE_TRANSITION_ENTER) {
            eventView.setText(R.string.activity_main_item_event_enter);
        } else {
            eventView.setText(R.string.activity_main_item_event_exit);
        }

        regionNameView.setText(logEvent.getRegionName());

        StringBuilder sb = new StringBuilder();
        RealmList<String> registeredRegions = logEvent.getRegisteredRegions();
        for (int i = 0; i < registeredRegions.size(); i++) {
            sb.append(i + 1).append(". ").append(registeredRegions.get(i));
            if (i + 1 < registeredRegions.size()) {
                sb.append("<br>");
            }
        }

        registeredRegionsView.setText(Html.fromHtml(sb.toString()));
    }
}
