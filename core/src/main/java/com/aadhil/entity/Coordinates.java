package com.aadhil.entity;

import java.io.Serializable;

public class Coordinates implements Serializable, Comparable<Coordinates> {
    private Double latitude;
    private Double longitude;

    public Coordinates() {}

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        if(latitude == coordinates.getLatitude() && longitude == coordinates.getLatitude()) {
            return 1;
        } else {
            return 0;
        }
    }
}
