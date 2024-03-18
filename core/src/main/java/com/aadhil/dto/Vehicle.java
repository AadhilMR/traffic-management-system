package com.aadhil.dto;

import com.aadhil.entity.Coordinates;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;

public class Vehicle {
    private Long id;
    private int speed;
    private Coordinates coordinates;
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
