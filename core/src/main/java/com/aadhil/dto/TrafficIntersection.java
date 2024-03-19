package com.aadhil.dto;

import com.aadhil.entity.Coordinates;
import com.aadhil.entity.TrafficLightStatus;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "traffic_intersection")
public class TrafficIntersection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TrafficLightStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "coordinates_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "coordinates_longitude"))
    })
    private Coordinates coordinates;

    @Column(name = "time")
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
