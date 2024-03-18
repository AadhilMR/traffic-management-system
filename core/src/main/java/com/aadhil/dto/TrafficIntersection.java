package com.aadhil.dto;

import com.aadhil.entity.Coordinates;
import com.aadhil.entity.TrafficLightStatus;

public class TrafficIntersection {
    private Long id;
    private TrafficLightStatus status;
    private Coordinates coordinates;
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrafficLightStatus getStatus() {
        return status;
    }

    public void setStatus(TrafficLightStatus status) {
        this.status = status;
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
