package com.example.sergey.geofencingwithrealmapplication.Service.util;

import android.location.Location;
import android.support.annotation.NonNull;

import com.example.sergey.geofencingwithrealmapplication.Model.Region;
import com.example.sergey.geofencingwithrealmapplication.Model.RegionsDatabase;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public final class NearRegionsUtil {

    private NearRegionsUtil() {
    }

    @NonNull
    public static List<Region> getNearRegions(@NonNull Region lastRegion, @NonNull Location location) {
        PointD lineVector = getLineVector(getLineKoef(location));
        LatLng driverPosition = getDriverPositionFromLocation(location);
        return getNearRegions(lastRegion, lineVector, driverPosition);
    }

    @NonNull
    private static LineKoef getLineKoef(@NonNull Location location) {
        double phi = location.getBearing();

        double x0 = location.getLatitude();
        double y0 = location.getLongitude();

        if (phi < 90) {
            double radians = Math.toRadians(phi);
            double a = Math.sin(radians);
            double b = Math.cos(radians);
            double c = -(a * x0 + b * y0);
            return new LineKoef(a, b, c);
        }

        if (phi < 180) {
            double radians = Math.toRadians(phi);
            double a = Math.sin(radians);
            double b = -Math.cos(radians);
            double c = -(a * x0 + b * y0);
            return new LineKoef(a, b, c);
        }

        if (phi < 270) {
            double radians = Math.toRadians(270 - phi);
            double a = -Math.cos(radians);
            double b = -Math.sin(radians);
            double c = -(a * x0 + b * y0);
            return new LineKoef(a, b, c);
        }

        double radians = Math.toRadians(phi - 270);
        double a = -Math.cos(radians);
        double b = Math.sin(radians);
        double c = -(a * x0 + b * y0);
        return new LineKoef(a, b, c);
    }

    @NonNull
    private static PointD getLineVector(@NonNull LineKoef lineKoef) {
        return new PointD(0, -lineKoef.getC() / lineKoef.getB());
    }

    @NonNull
    private static LatLng getDriverPositionFromLocation(@NonNull Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    @NonNull
    private static List<Region> getNearRegions(@NonNull Region lastRegion,
                                               @NonNull PointD lineVector,
                                               @NonNull LatLng driverPosition) {

        List<Region> filteredRegions = new ArrayList<>();

        RegionTypePosition lastRegionTypePosition
                = getRegionTypePosition(lineVector, getRegionVector(driverPosition, lastRegion));

        for (Region region : RegionsDatabase.getInstance().getRegions()) {
            PointD regionVector = getRegionVector(driverPosition, region);
            if (lastRegionTypePosition != getRegionTypePosition(lineVector, regionVector)) {
                filteredRegions.add(region);
            }
        }

        return filteredRegions;
    }

    @NonNull
    private static PointD getRegionVector(@NonNull LatLng driverPosition,
                                          @NonNull Region region) {
        double regionLatitude = region.getCenter().getLatitude();
        double regionLongitude = region.getCenter().getLongitude();

        double vectorX = regionLatitude - driverPosition.latitude;
        double vectorY = regionLongitude - driverPosition.longitude;

        return new PointD(vectorX, vectorY);
    }

    @NonNull
    private static RegionTypePosition getRegionTypePosition(@NonNull PointD lineVector,
                                                            @NonNull PointD regionVector) {
        return lineVector.getX() * regionVector.getY() - lineVector.getY() * regionVector.getX() > 0
                ? RegionTypePosition.UNDER_LINE : RegionTypePosition.OVER_LINE;
    }

    private enum RegionTypePosition {
        UNDER_LINE,
        OVER_LINE
    }
}