package com.aadhil.analyze.impl;

import com.aadhil.analyze.remote.TimeSpecifier;
import com.aadhil.dto.Vehicle;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class TimeSpecifyBean implements TimeSpecifier {
    @Override
    public List<List<Vehicle>> specify(List<Vehicle> vehicleList) {
        List<List<Vehicle>> timeList = new ArrayList<>();
        List<Vehicle> hourGroup1 = new ArrayList<>();
        List<Vehicle> hourGroup2 = new ArrayList<>();
        List<Vehicle> hourGroup3 = new ArrayList<>();
        List<Vehicle> hourGroup4 = new ArrayList<>();

        for(Vehicle vehicle : vehicleList) {
            int hour = LocalDateTime
                    .parse(vehicle.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    .getHour();

            if (hour < 6) {
                hourGroup1.add(vehicle);
            } else if (hour < 12) {
                hourGroup2.add(vehicle);
            } else if (hour < 18) {
                hourGroup3.add(vehicle);
            } else {
                hourGroup4.add(vehicle);
            }
        }

        timeList.add(hourGroup1);
        timeList.add(hourGroup2);
        timeList.add(hourGroup3);
        timeList.add(hourGroup4);

        return timeList;
    }
}
