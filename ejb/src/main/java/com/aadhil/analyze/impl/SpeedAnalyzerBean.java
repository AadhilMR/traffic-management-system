package com.aadhil.analyze.impl;

import com.aadhil.analyze.remote.SpeedAnalyzer;
import com.aadhil.dto.Vehicle;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;

import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SpeedAnalyzerBean implements SpeedAnalyzer {
    @Override
    public double analyzeSpeed(List<Vehicle> vehicleList) {
        if(vehicleList == null || vehicleList.isEmpty()) {
            return 0.0;
        }

        int totalSpeed = 0;

        for(Vehicle vehicle : vehicleList) {
            totalSpeed += vehicle.getSpeed();
        }

        return (double) totalSpeed / vehicleList.size();
    }
}
