package com.aadhil.central.remote;

import com.aadhil.dto.TrafficIntersection;

import java.util.List;
import java.util.Map;

public interface TrafficIntersectionList {
    void addTrafficIntersection(TrafficIntersection trafficIntersection);

    Map<String, TrafficIntersection> getTrafficIntersectionList();
}
