package com.aadhil.service;

import com.aadhil.dto.TrafficIntersection;
import com.aadhil.entity.AverageSpeed;
import com.aadhil.entity.CongestionLevel;
import com.aadhil.entity.Coordinates;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.util.List;
import java.util.Map;

public class JSONEncodingService {
    public String getJsonResponse(Map<String, AverageSpeed> averageSpeedMap,
                                  Map<String, CongestionLevel> congestionLevelMap,
                                  List<TrafficIntersection> trafficIntersectionList) {

        return getJsonObject(averageSpeedMap, congestionLevelMap, trafficIntersectionList).toString();
    }

    private JsonObject getJsonObject(Map<String, AverageSpeed> averageSpeedMap,
                                     Map<String, CongestionLevel> congestionLevelMap,
                                     List<TrafficIntersection> trafficIntersectionList) {

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("averageSpeed", getAverageSpeedObject(averageSpeedMap));
        jsonObjectBuilder.add("congestionLevel", getCongestionLevelObject(congestionLevelMap));
        jsonObjectBuilder.add("trafficIntersection", getTrafficIntersectionObject(trafficIntersectionList));

        return jsonObjectBuilder.build();
    }

    private JsonObject getAverageSpeedObject(Map<String, AverageSpeed> averageSpeedMap) {
        JsonObjectBuilder averageSpeedBuilder = Json.createObjectBuilder();

        for(Map.Entry<String, AverageSpeed> entry : averageSpeedMap.entrySet()) {
            averageSpeedBuilder.add(entry.getKey(), String.valueOf(entry.getValue().getAvg_speed()));
        }
        return averageSpeedBuilder.build();
    }

    private JsonObject getCongestionLevelObject(Map<String, CongestionLevel> congestionLevelMap) {
        JsonObjectBuilder congestionLevelBuilder = Json.createObjectBuilder();

        for(Map.Entry<String, CongestionLevel> entry : congestionLevelMap.entrySet()) {
            congestionLevelBuilder.add(entry.getKey(), entry.getValue().toString());
        }
        return congestionLevelBuilder.build();
    }

    private JsonObject getTrafficIntersectionObject(List<TrafficIntersection> trafficIntersectionList) {
        JsonObjectBuilder trafficIntersectionBuilder = Json.createObjectBuilder();

        for(TrafficIntersection trafficIntersection : trafficIntersectionList) {
            String trafficIntersectionId = identifyTrafficIntersection(trafficIntersection.getCoordinates());
            trafficIntersectionBuilder.add(trafficIntersectionId, trafficIntersection.getStatus().toString());
        }

        return trafficIntersectionBuilder.build();
    }

    private String identifyTrafficIntersection(Coordinates coordinates) {
        double t1_latitude = 7.490468;
        double t1_longitude = 80.358728;
        double t2_latitude = 7.488809;
        double t2_longitude = 80.361947;

        Coordinates t1_coordinate = new Coordinates(t1_latitude, t1_longitude);
        Coordinates t2_coordinate = new Coordinates(t2_latitude, t2_longitude);

        if(coordinates.compareTo(t1_coordinate) == 1) {
            return "t1";
        }

        if(coordinates.compareTo(t2_coordinate) == 1) {
            return "t2";
        }
        return null;
    }
}
