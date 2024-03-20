package com.aadhil.analyze.remote;

import com.aadhil.entity.CongestionLevel;
import jakarta.ejb.Remote;

@Remote
public interface CongestionAnalyzer {
    CongestionLevel analyzeTrafficPattenr(double averageSpeed);
}
