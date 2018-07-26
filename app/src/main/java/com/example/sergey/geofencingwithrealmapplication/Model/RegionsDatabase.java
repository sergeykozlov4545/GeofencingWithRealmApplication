package com.example.sergey.geofencingwithrealmapplication.Model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
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

    @NonNull
    public OrderedRealmCollection<Region> getRegions() {
        return realm.where(Region.class).findAll();
    }

    @Nullable
    public Region getRegion(@NonNull String regionId) {
        return realm.where(Region.class).equalTo("id", regionId).findFirst();
    }

    public void addRegion(@NonNull String name, @NonNull RealmLatLng center, int radius) {
        realm.executeTransaction(r -> {
            String id = UUID.randomUUID().toString();
            r.insertOrUpdate(new Region(id, name, center, radius));
        });
    }

    public void removeRegion(@NonNull String regionId) {
        Region region = realm.where(Region.class).equalTo("id", regionId).findFirst();
        if (region == null) {
            return;
        }

        realm.executeTransaction(r -> region.deleteFromRealm());
    }

    public void updateRegion(@NonNull String regionId,
                             @NonNull String name,
                             @NonNull LatLng center,
                             int radius) {
        Region region = realm.where(Region.class).equalTo("id", regionId).findFirst();
        if (region == null) {
            return;
        }

        realm.executeTransaction(r -> {
            RealmLatLng realmCenter = region.getCenter();
            realmCenter.setLatitude(center.latitude);
            realmCenter.setLongitude(center.longitude);

            region.setName(name);
            region.setRadius(radius);
        });
    }

    public List<String> getRegisteredIds() {
        RealmResults<RegisteredRegions> data = realm.where(RegisteredRegions.class).findAll();
        List<String> ids = new ArrayList<>();

        for (RegisteredRegions regions : data) {
            ids.add(regions.getRegionId());
        }

        return ids;
    }

    public void removeAllRegisteredRegions() {
        realm.executeTransaction(r -> r.delete(RegisteredRegions.class));
    }

    public void addRegisteredRegion(@NonNull List<Region> regions) {
        realm.executeTransaction(r -> {
            for (Region region : regions) {
                r.insertOrUpdate(new RegisteredRegions(region.getId()));
            }
        });
    }
}
