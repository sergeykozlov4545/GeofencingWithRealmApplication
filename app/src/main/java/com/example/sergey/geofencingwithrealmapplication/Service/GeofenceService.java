package com.example.sergey.geofencingwithrealmapplication.Service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceService extends IntentService {

    private static final String TAG = "GeofenceService";

    private static final int REQUEST_LOCATION_UPDATES_PERIOD = 1000;
    private static final int REQUEST_LOCATION_UPDATES_DISTANCE_IN_METERS = 10;

    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: location = " + location);

            // TODO: 24.07.18 Отрегистрировать все текущие зоны (если был exit)
            // TODO: 24.07.18 Получить ближашие зоны по координате (1 точно будет - откуда я вышел)
            // TODO: 24.07.18 Зарегать полученные зоны

            removeLocationUpdates();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: provider = " + provider + ", status = " + status);
            // TODO: 24.07.18 Может тут как-то реагировать
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: provider = " + provider);
            // TODO: 24.07.18 Может тут как-то реагировать
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: provider = " + provider);
            // TODO: 24.07.18 Может тут как-то реагировать
        }
    };

    public GeofenceService() {
        super(TAG);

        locationManager =
                (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent != null && !geofencingEvent.hasError()) {
            if (isTransitionEnter(geofencingEvent.getGeofenceTransition())) {
                // TODO: 24.07.18 Вошли в зону, а значит чето делаем (notification, лог на MainActivity и т.д.)
                return;
            }

            requestLocationUpdates();
        }
    }

    private boolean isTransitionEnter(int transitionType) {
        return transitionType == Geofence.GEOFENCE_TRANSITION_ENTER;
    }

    @SuppressLint("MissingPermission")
    private void requestLocationUpdates() {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                REQUEST_LOCATION_UPDATES_PERIOD,
                REQUEST_LOCATION_UPDATES_DISTANCE_IN_METERS,
                locationListener
        );
    }

    private void removeLocationUpdates() {
        locationManager.removeUpdates(locationListener);
    }
}
