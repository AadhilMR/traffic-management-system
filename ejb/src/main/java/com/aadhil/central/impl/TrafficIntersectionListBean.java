package com.aadhil.central.impl;

import com.aadhil.central.remote.TrafficIntersectionList;
import com.aadhil.dto.TrafficIntersection;
import jakarta.ejb.Stateful;

import java.util.HashMap;
import java.util.Map;

@Stateful(name = "TrafficBean")
public class TrafficIntersectionListBean implements TrafficIntersectionList {

    Map<String, TrafficIntersection> trafficIntersectionList = new HashMap<>();

    @Override
    public void addTrafficIntersection(TrafficIntersection trafficIntersection) {
        trafficIntersectionList.put(trafficIntersection.getTime(), trafficIntersection);
    }

    @Override
    public Map<String, TrafficIntersection> getTrafficIntersectionList() {
        return trafficIntersectionList;
    }
}
