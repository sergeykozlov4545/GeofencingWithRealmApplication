package com.example.sergey.geofencingwithrealmapplication.App;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GeofencingRealmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
    }

    private void initRealm() {
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
