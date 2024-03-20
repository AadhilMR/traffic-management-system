package com.aadhil.analyze.impl;

import com.aadhil.analyze.remote.CongestionAnalyzer;
import com.aadhil.entity.CongestionLevel;
import jakarta.ejb.Singleton;

@Singleton
public class CongestionAnalyzerBean implements CongestionAnalyzer {
    private static final int MAXIMUM_SPEED = 200;
    private static final int MAXIMUM_VEHICLE_COUNT = 100;

    @Override
    public CongestionLevel analyzeTrafficPattenr(double averageSpeed, int vehicleCount) {
        double congestionScore = calculateCongestionScore(averageSpeed, vehicleCount);

        if(congestionScore >= 0.75) {
            return CongestionLevel.HEAVY;
        } else if(congestionScore >= 0.4) {
            return CongestionLevel.NORMAL;
        } else {
            return CongestionLevel.LIGHT;
        }
    }

    private double calculateCongestionScore(double averageSpeed, int vehicleCount) {
        double normalizedSpeed = normalizeSpeed(averageSpeed);
        double normalizedVehicleCount = normalizeVehicleCount(vehicleCount);

        return (normalizedSpeed + normalizedVehicleCount) / 2;
    }

    private double normalizeSpeed(double averageSpeed) {
        return Math.min(1.0, averageSpeed/MAXIMUM_SPEED);
    }

    private double normalizeVehicleCount(int vehicleCount) {
        return Math.min(1.0, vehicleCount/MAXIMUM_VEHICLE_COUNT);
    }
}
