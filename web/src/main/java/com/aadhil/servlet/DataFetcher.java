package com.aadhil.servlet;

import com.aadhil.analyze.remote.*;
import com.aadhil.dto.TrafficIntersection;
import com.aadhil.dto.Vehicle;
import com.aadhil.entity.AverageSpeed;
import com.aadhil.entity.CongestionLevel;
import com.aadhil.service.DataAnalyzeService;
import com.aadhil.service.DatabaseService;
import com.aadhil.service.JSONEncodingService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DataFetcher", value = "/dataFetcher")
public class DataFetcher extends HttpServlet {
    @EJB
    DatabaseManager databaseManager;
    @EJB
    RouteSpecifier routeSpecifier;
    @EJB
    TimeSpecifier timeSpecifier;
    @EJB
    SpeedAnalyzer speedAnalyzer;
    @EJB
    CongestionAnalyzer congestionAnalyzer;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        DatabaseService databaseService = new DatabaseService(databaseManager);
        DataAnalyzeService analyzeService = new DataAnalyzeService(routeSpecifier, timeSpecifier, speedAnalyzer, congestionAnalyzer);

        // Get Vehicle List
        List<Vehicle> vehicleList = databaseService.fetchVehiclesList();

        // Analyze Average Speed
        // Return 8 entries (2 routes, 4 timelines)
        Map<String, AverageSpeed> averageSpeedMap = analyzeService.getAverageSpeedMap(vehicleList);

        // Save Average Speed List
        for(Map.Entry<String, AverageSpeed> entry : averageSpeedMap.entrySet()) {
            AverageSpeed averageSpeed = entry.getValue();
            databaseService.save(averageSpeed);
        }

        // Analyze Congestion
        // Return 8 entries (2 routes, 4 timelines)
        Map<String, CongestionLevel> congestionLevelMap = analyzeService.analyzeCongestion(averageSpeedMap);

        // Get Traffic Intersection List
        List<TrafficIntersection> trafficIntersectionList = databaseService.fetchTrafficIntersectionsList();

        // Get Json String
        JSONEncodingService jsonService = new JSONEncodingService();
        String jsonString = jsonService.getJsonResponse(averageSpeedMap, congestionLevelMap, trafficIntersectionList);
        response.getWriter().print(jsonString);
    }
}
