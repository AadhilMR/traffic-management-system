package com.aadhil.dto;

import com.aadhil.entity.Coordinates;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "speed")
    private int speed;

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
