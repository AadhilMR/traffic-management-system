package com.aadhil.entity;

import javax.swing.plaf.PanelUI;
import java.io.Serializable;

public class Coordinates implements Serializable {
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
}
