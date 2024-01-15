package com.medhead.emergency.service;

import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;

import java.util.List;

public interface TravelTimeCalculator {

    /**
     * Calculate the travel time by driving between two geographic points.
     * @param departure geographic coordinates
     * @param arrival geographic coordinates
     * @return time in milliseconds
     */
    int calculateTravelTimeBetweenTwoPoints(GeographicCoordinates departure, GeographicCoordinates arrival);

    /**
     * Find the closest medical center depends on time travel by driving.
     * @param medicalCenters
     * @return closest medical center.
     */
    MedicalCenterWithTravelTime findClosestMedicalCenter(GeographicCoordinates position, List<MedicalCenter> medicalCenters);

}
