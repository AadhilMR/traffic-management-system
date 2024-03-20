package com.aadhil.service;

import com.aadhil.analyze.remote.*;
import com.aadhil.dto.Vehicle;
import com.aadhil.entity.AverageSpeed;
import com.aadhil.entity.CongestionLevel;
import jakarta.ejb.EJB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataAnalyzeService {
    private RouteSpecifier routeSpecifier;
    private TimeSpecifier timeSpecifier;
    private SpeedAnalyzer speedAnalyzer;
    private CongestionAnalyzer congestionAnalyzer;

    public DataAnalyzeService(RouteSpecifier routeSpecifier, TimeSpecifier timeSpecifier,
                              SpeedAnalyzer speedAnalyzer, CongestionAnalyzer congestionAnalyzer) {
        this.routeSpecifier = routeSpecifier;
        this.timeSpecifier = timeSpecifier;
        this.speedAnalyzer = speedAnalyzer;
        this.congestionAnalyzer = congestionAnalyzer;
    }

    public Map<String, CongestionLevel> analyzeCongestion(Map<String, AverageSpeed> congestionDataMap) {
        Map<String, CongestionLevel> congestionLevelMap = new HashMap<>();

        for (Map.Entry<String, AverageSpeed> entry : congestionDataMap.entrySet()) {
            String key = entry.getKey();
            double averageSpeed = entry.getValue().getAvg_speed();

            CongestionLevel level = congestionAnalyzer.analyzeTrafficPattenr(averageSpeed);

            congestionLevelMap.put(key, level);
        }

        return congestionLevelMap;
    }

    public Map<String, AverageSpeed> getAverageSpeedMap(List<Vehicle> vehicleList) {
        Map<String, AverageSpeed> averageSpeedMap = new HashMap<>();

        // Get Route List
        List<List<Vehicle>> routeList = specifyRoute(vehicleList);

        // Get Time List
        for(int i=0; i<routeList.size(); i++) {
            List<List<Vehicle>> timeList = specifyTime(routeList.get(i));

            // Get Average Speed List
            for(int j=0; j<timeList.size(); j++) {
                List<Vehicle> groupedVehicleList = timeList.get(j);
                double averageSpeedValue = analyzeSpeed(groupedVehicleList);

                AverageSpeed averageSpeed = new AverageSpeed();
                averageSpeed.setAvg_speed(averageSpeedValue);
                averageSpeed.setDate(getDate());
                averageSpeed.setTime_period(i+1);

                String key = "r" + (i+1) + "_t" + (j+1);

                averageSpeedMap.put(key, averageSpeed);
            }
        }

        return averageSpeedMap;
    }

    private List<List<Vehicle>> specifyRoute(List<Vehicle> vehicleList) {
        return routeSpecifier.specify(vehicleList);
    }

    private List<List<Vehicle>> specifyTime(List<Vehicle> vehicleList) {
        return timeSpecifier.specify(vehicleList);
    }

    private double analyzeSpeed(List<Vehicle> vehicleList) {
        return speedAnalyzer.analyzeSpeed(vehicleList);
    }

    private Date getDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());
            return sdf.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
