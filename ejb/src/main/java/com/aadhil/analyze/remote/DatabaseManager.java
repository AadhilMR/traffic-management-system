package com.aadhil.analyze.remote;

import com.aadhil.dto.TrafficIntersection;
import com.aadhil.dto.Vehicle;
import com.aadhil.entity.AverageSpeed;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface DatabaseManager {
    void saveAverageSpeed(AverageSpeed averageSpeed);

    List<Vehicle> getVehiclesList(String date);

    List<TrafficIntersection> getTrafficIntersectionsList(String date);
}
