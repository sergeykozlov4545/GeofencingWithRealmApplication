package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationUtil {

    private static final String TAG = "LocationUtil";

    private static final int REQUEST_LOCATION_UPDATES_PERIOD = 1000;
    private static final int REQUEST_LOCATION_UPDATES_DISTANCE_IN_METERS = 10;

    private GeofenceRegisterUtil geofenceRegisterUtil;

    private LocationManager locationManager;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            geofenceRegisterUtil.unregisterAllRegions();
            geofenceRegisterUtil.registerNearRegions(location);

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

    public LocationUtil(Context context) {
        geofenceRegisterUtil = new GeofenceRegisterUtil(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public void requestLocationUpdates() {
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
