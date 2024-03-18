package com.aadhil.central.impl;

import com.aadhil.central.remote.VehicleList;
import com.aadhil.dto.Vehicle;
import jakarta.ejb.Stateful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful(name = "VehicleBean")
public class VehicleListBean implements VehicleList {
    Map<String, Vehicle> vehicleList = new HashMap<>();

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicleList.put(vehicle.getTime(), vehicle);
    }

    @Override
    public Map<String, Vehicle> getVehicleList() {
        return vehicleList;
    }
}
