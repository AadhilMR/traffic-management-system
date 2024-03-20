package com.aadhil.analyze.impl;

import com.aadhil.analyze.remote.CongestionAnalyzer;
import com.aadhil.entity.CongestionLevel;
import jakarta.ejb.Singleton;

@Singleton
public class CongestionAnalyzerBean implements CongestionAnalyzer {
    private static final int MAXIMUM_SPEED = 80;

    @Override
    public CongestionLevel analyzeTrafficPattenr(double averageSpeed) {
        double congestionScore = averageSpeed/MAXIMUM_SPEED;

        if(congestionScore >= 0.625) { // Speed is above 50 KmPH
            return CongestionLevel.LIGHT;
        } else if(congestionScore >= 0.125) { // Speed is between 10-50 KmPH
            return CongestionLevel.NORMAL;
        } else { // Speed is below 10 KmPH
            return CongestionLevel.HEAVY;
        }
    }
}
