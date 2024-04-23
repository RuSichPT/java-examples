package com.github.rusichpt.filter.service;

import com.github.rusichpt.filter.controller.data.TaxiRide;
import org.springframework.stereotype.Service;

@Service
public class TaxiFareCalculatorService {

    public String calculateFare(TaxiRide taxiRide) {
        return String.valueOf((Long) (Boolean.TRUE.equals(taxiRide.getIsNightSurcharge())
                ? taxiRide.getDistanceInMile() * 10 + 100
                : taxiRide.getDistanceInMile() * 10));
    }
}
