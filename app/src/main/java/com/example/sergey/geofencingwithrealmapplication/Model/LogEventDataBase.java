package com.example.sergey.geofencingwithrealmapplication.Model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergey.geofencingwithrealmapplication.App.GeofencingRealmApplication;
import com.example.sergey.geofencingwithrealmapplication.R;
import com.google.android.gms.location.Geofence;

import java.util.List;
import java.util.UUID;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmList;

public final class LogEventDataBase {

    private static volatile LogEventDataBase instance;
    private Realm realm;

    @NonNull
    public static LogEventDataBase getInstance() {
        if (instance == null) {
            synchronized (LogEventDataBase.class) {
                if (instance == null) {
                    instance = new LogEventDataBase();
                }
            }
        }

        return instance;
    }

    private LogEventDataBase() {
        realm = Realm.getDefaultInstance();
    }

    @NonNull
    public OrderedRealmCollection<LogEvent> getEvents() {
        return realm.where(LogEvent.class).findAll();
    }

    public void addEvent(int transitionType,
                         @Nullable Region triggeringRegion,
                         @NonNull List<Region> registeredRegions) {

        String id = UUID.randomUUID().toString();
        String triggeringRegionName = triggeringRegion == null ? "" : triggeringRegion.getName();

        if (transitionType == Geofence.GEOFENCE_TRANSITION_EXIT && triggeringRegion != null) {
            triggeringRegionName += " (Ближайшая точка)";
        }

        RealmList<String> registeredNamesRegions = getRegisteredNamesRegions(registeredRegions);

        LogEvent event = new LogEvent(id, transitionType, triggeringRegionName, registeredNamesRegions);

        realm.executeTransaction(r -> {
            r.insertOrUpdate(event);
            showLogToast(event);
        });
    }

    public void removeAllEvents() {
        realm.executeTransaction(r -> getEvents().deleteAllFromRealm());
    }

    @NonNull
    private RealmList<String> getRegisteredNamesRegions(@NonNull List<Region> registeredRegions) {
        RealmList<String> registeredNamesRegions = new RealmList<>();
        for (Region region : registeredRegions) {
            registeredNamesRegions.add(region.getName());
        }
        return registeredNamesRegions;
    }

    private void showLogToast(@NonNull LogEvent event) {
        View view = LayoutInflater.from(GeofencingRealmApplication.applicationContext)
                .inflate(R.layout.actvity_main_list_item, null);

        initToastEventView(view, event);
        initToastRegionNameView(view, event);
        initToastRegisteredRegionsView(view, event);

        showToastWithCustomView(view);
    }

    private void initToastEventView(View parrent, @NonNull LogEvent event) {
        TextView eventView = parrent.findViewById(R.id.eventView);
        eventView.setText(event.getTransitionType() == Geofence.GEOFENCE_TRANSITION_ENTER
                ? R.string.activity_main_item_event_enter : R.string.activity_main_item_event_exit);
    }

    private void initToastRegionNameView(View parrent, @NonNull LogEvent event) {
        TextView regionNameView = parrent.findViewById(R.id.regionNameView);
        regionNameView.setText(event.getRegionName());
    }

    private void initToastRegisteredRegionsView(View parrent, @NonNull LogEvent event) {
        StringBuilder sb = new StringBuilder();
        RealmList<String> registeredRegions = event.getRegisteredRegions();
        for (int i = 0; i < registeredRegions.size(); i++) {
            sb.append(i + 1).append(". ").append(registeredRegions.get(i));
            if (i + 1 < registeredRegions.size()) {
                sb.append("\n");
            }
        }
        TextView registeredRegionsView = parrent.findViewById(R.id.registeredRegionsView);
        registeredRegionsView.setText(sb.toString());
    }

    private void showToastWithCustomView(View view) {
        Toast toast = new Toast(view.getContext());
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
