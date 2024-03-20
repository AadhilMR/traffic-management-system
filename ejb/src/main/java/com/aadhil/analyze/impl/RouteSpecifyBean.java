package com.aadhil.analyze.impl;

import com.aadhil.analyze.remote.RouteSpecifier;
import com.aadhil.dto.Vehicle;
import com.aadhil.entity.Coordinates;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class RouteSpecifyBean implements RouteSpecifier {
    private final static double A1_startX = 7.4950278;
    private final static double A1_startY = 80.352666;
    private final static double A1_endX = 7.489311;
    private final static double A1_endY = 80.360923;

    private final static double A6_startX = 7.492702;
    private final static double A6_startY = 80.364822;
    private final static double A6_endX = 7.484192;
    private final static double A6_endY = 80.359437;

    @Override
    public List<List<Vehicle>> specify(List<Vehicle> vehicleList) {
        List<List<Vehicle>> routeList = new ArrayList<>();
        List<Vehicle> routeA1List = new ArrayList<>();
        List<Vehicle> routeA6List = new ArrayList<>();

        Coordinates coordinates;

        for(Vehicle vehicle : vehicleList) {
            coordinates = vehicle.getCoordinates();
            String route = identifyRoute(coordinates.getLatitude(), coordinates.getLongitude());

            if(route.equals("A1")) {
                routeA1List.add(vehicle);
            } else if(route.equals("A6")) {
                routeA6List.add(vehicle);
            }
        }
        routeList.add(routeA1List);
        routeList.add(routeA6List);

        return routeList;
    }

    private String identifyRoute(double pointX, double pointY) {
        double distanceToA1 = distanceToLineSegment(A1_startX, A1_startY, A1_endX, A1_endY, pointX, pointY);
        double distanceToA6 = distanceToLineSegment(A6_startX, A6_startY, A6_endX, A6_endY, pointX, pointY);

        if (distanceToA1 < distanceToA6) {
            return "A1";
        } else {
            return "A6";
        }
    }

    private double distanceToLineSegment(double startX, double startY, double endX, double endY, double pointX, double pointY) {
        double lengthSquared = (endX - startX) * (endX - startX) + (endY - startY) * (endY - startY);
        double sqrt = Math.sqrt((pointX - startX) * (pointX - startX) + (pointY - startY) * (pointY - startY));

        if (lengthSquared == 0.0) {
            return sqrt;
        }

        double t = ((pointX - startX) * (endX - startX) + (pointY - startY) * (endY - startY)) / lengthSquared;

        if (t < 0) {
            return sqrt;
        }

        if (t > 1) {
            return Math.sqrt((pointX - endX) * (pointX - endX) + (pointY - endY) * (pointY - endY));
        }

        double projectionX = startX + t * (endX - startX);
        double projectionY = startY + t * (endY - startY);

        return Math.sqrt((pointX - projectionX) * (pointX - projectionX) + (pointY - projectionY) * (pointY - projectionY));
    }
}
