package com.example.sergey.geofencingwithrealmapplication.Model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
        RealmList<String> registeredNamesRegions = getRegisteredNamesRegions(registeredRegions);

        LogEvent event = new LogEvent(id, transitionType, triggeringRegionName, registeredNamesRegions);

        realm.executeTransaction(r -> r.insertOrUpdate(event));
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
}
