package com.example.sergey.geofencingwithrealmapplication.Model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;

public final class RegionsDatabase {

    private static RegionsDatabase instance;
    private Realm realm;

    @NonNull
    public static RegionsDatabase getInstance() {
        if (instance == null) {
            synchronized (RegionsDatabase.class) {
                if (instance == null) {
                    instance = new RegionsDatabase();
                }
            }
        }

        return instance;
    }

    private RegionsDatabase() {
        realm = Realm.getDefaultInstance();
    }

    public void getRegions(@NonNull LoadRegionsCallback callback) {
        RealmResults<Region> data = realm.where(Region.class).findAll();

        if (data.size() == 0) {
            callback.dataIsEmpty();
            return;
        }

        callback.dataIsLoaded(data);
    }

    @Nullable
    public Region getRegion(@NonNull String regionId) {
        return realm.where(Region.class).equalTo("id", regionId).findFirst();
    }

    public void addRegion(@NonNull String name, double latitude, double longitude, int radius) {
        realm.executeTransaction(r -> {
            String id = UUID.randomUUID().toString();
            r.insertOrUpdate(new Region(id, name, latitude, longitude, radius));
        });
    }

    public void removeRegion(@NonNull String regionId) {
        Region region = realm.where(Region.class).equalTo("id", regionId).findFirst();
        if (region == null) {
            return;
        }

        realm.executeTransaction(r -> region.deleteFromRealm());
    }

    public void updateRegion(@NonNull String regionId, @NonNull String name, double latitude, double longitude, int radius) {
        Region region = realm.where(Region.class).equalTo("id", regionId).findFirst();
        if (region == null) {
            return;
        }

        realm.executeTransaction(r -> {
            region.setName(name);
            region.setLatitude(latitude);
            region.setLongitude(longitude);
            region.setRadius(radius);
        });
    }

    public interface LoadRegionsCallback {
        void dataIsLoaded(@NonNull OrderedRealmCollection<Region> data);

        void dataIsEmpty();
    }

}
