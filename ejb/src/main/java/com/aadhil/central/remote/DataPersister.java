package com.aadhil.central.remote;

import com.aadhil.dto.TrafficIntersection;
import com.aadhil.dto.Vehicle;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface DataPersister {
    void save(Vehicle vehicle);

    void save(TrafficIntersection trafficIntersection);
}
