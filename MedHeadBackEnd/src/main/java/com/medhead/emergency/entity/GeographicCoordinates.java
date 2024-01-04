package com.medhead.emergency.entity;

public class GeographicCoordinates {

    public GeographicCoordinates(
            double latitudeP,
            double longitudeP
    ) {
        latitude = latitudeP;
        longitude = longitudeP;
    }

    private double latitude;

    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitudeP) {
        latitude = latitudeP;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitudeP) {
        longitude = longitudeP;
    }
}
