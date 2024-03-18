package com.aadhil.central.remote;

import com.aadhil.dto.Vehicle;

import java.util.List;
import java.util.Map;

public interface VehicleList {
    void addVehicle(Vehicle vehicle);

    Map<String, Vehicle> getVehicleList();
}
