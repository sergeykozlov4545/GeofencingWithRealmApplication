package com.example.sergey.geofencingwithrealmapplication.Model;

import android.support.annotation.NonNull;

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

    public void addEvent(int transitionType, @NonNull String regionName, @NonNull RealmList<String> registeredRegions) {
        realm.executeTransaction(r -> {
            String id = UUID.randomUUID().toString();
            r.insertOrUpdate(new LogEvent(id, transitionType, regionName, registeredRegions));
        });
    }

    public void removeAllEvents() {
        realm.executeTransaction(r -> getEvents().deleteAllFromRealm());
    }
}
