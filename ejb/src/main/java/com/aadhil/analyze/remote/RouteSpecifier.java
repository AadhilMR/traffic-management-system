package com.aadhil.analyze.remote;

import com.aadhil.dto.Vehicle;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface RouteSpecifier {
    List<List<Vehicle>> specify(List<Vehicle> vehicleList);
}
