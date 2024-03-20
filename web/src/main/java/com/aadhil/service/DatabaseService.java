package com.aadhil.service;

import com.aadhil.analyze.remote.DatabaseManager;
import com.aadhil.dto.TrafficIntersection;
import com.aadhil.dto.Vehicle;
import com.aadhil.entity.AverageSpeed;
import jakarta.ejb.EJB;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DatabaseService {
    private DatabaseManager databaseManager;

    public DatabaseService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void save(AverageSpeed averageSpeed) {
        databaseManager.saveAverageSpeed(averageSpeed);
    }

    public List<Vehicle> fetchVehiclesList() {
        return databaseManager.getVehiclesList(today());
    }

    public List<TrafficIntersection> fetchTrafficIntersectionsList() {
        return databaseManager.getTrafficIntersectionsList(currentDateTime());
    }

    private String today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        return today;
    }

    private String currentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();

        if(dateTime.getMinute() % 2 != 0) {
            dateTime = dateTime.minusMinutes(1);
        }

        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
