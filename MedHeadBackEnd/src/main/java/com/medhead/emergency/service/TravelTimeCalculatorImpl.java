package com.medhead.emergency.service;

import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;

import java.util.List;

public class TravelTimeCalculatorImpl implements TravelTimeCalculator {

    public TravelTimeCalculatorImpl() {
    }

    @Override
    public int calculateTravelTimeBetweenTwoPoints(GeographicCoordinates departure, GeographicCoordinates arrival) {
        //TODO
        return 0;
    }

    @Override
    public MedicalCenter findClosestMedicalCenter(List<MedicalCenter> medicalCenters) {
        //TODO
        return null;
    }
}
