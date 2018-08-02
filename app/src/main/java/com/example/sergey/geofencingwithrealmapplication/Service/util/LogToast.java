package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergey.geofencingwithrealmapplication.Model.LogEvent;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.google.android.gms.location.Geofence;

import io.realm.RealmList;

public final class LogToast {

    private LogToast() {
    }

    public static void show(@NonNull Context context, @NonNull LogEvent event, int toastDuration) {
        Toast toast = new Toast(context);
        toast.setView(getToastView(context, event));
        toast.setDuration(toastDuration);
        toast.show();
    }

    private static View getToastView(@NonNull Context context, @NonNull LogEvent event) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.actvity_main_list_item, null);

        initToastEventView(view, event);
        initToastRegionNameView(view, event);
        initToastRegisteredRegionsView(view, event);

        return view;
    }

    private static void initToastEventView(View parent, @NonNull LogEvent event) {
        TextView eventView = parent.findViewById(R.id.eventView);
        eventView.setText(event.getTransitionType() == Geofence.GEOFENCE_TRANSITION_ENTER
                ? R.string.activity_main_item_event_enter : R.string.activity_main_item_event_exit);
    }

    private static void initToastRegionNameView(View parent, @NonNull LogEvent event) {
        TextView regionNameView = parent.findViewById(R.id.regionNameView);
        regionNameView.setText(event.getRegionName());
    }

    private static void initToastRegisteredRegionsView(View parent, @NonNull LogEvent event) {
        StringBuilder sb = new StringBuilder();
        RealmList<String> registeredRegions = event.getRegisteredRegions();
        for (int i = 0; i < registeredRegions.size(); i++) {
            sb.append(i + 1).append(". ").append(registeredRegions.get(i));
            if (i + 1 < registeredRegions.size()) {
                sb.append("\n");
            }
        }
        TextView registeredRegionsView = parent.findViewById(R.id.registeredRegionsView);
        registeredRegionsView.setText(sb.toString());
    }
}
