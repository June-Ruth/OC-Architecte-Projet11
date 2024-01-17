package com.medhead.emergency.service;

import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelTimeCalculatorTest {

    private static TravelTimeCalculator travelTimeCalculator;

    private List<MedicalCenter> medicalCenters;
    private MedicalCenter medicalCenter1;
    private MedicalCenter medicalCenter2;
    private MedicalCenter medicalCenter3;
    private GeographicCoordinates testPosition;

    @BeforeEach
    void beforeEach() {
        travelTimeCalculator = new TravelTimeCalculatorImpl();
        medicalCenter1 = new MedicalCenter(17970, "Walton Community Hospital - Virgin Care Services Ltd", "", "Rodney Road", "", "Walton-on-Thames", "Surrey", "KT12 3LD", new GeographicCoordinates(51.379997253417969, -0.40604206919670105), new ArrayList<>());
        medicalCenter2 = new MedicalCenter(18101, "North Somerset Community Partnership Cic HQ", "Castlewood", "Tickenham Road", "", "Clevedon", "Avon", "BS21 6AB", new GeographicCoordinates(51.439693450927734, -2.8400685787200928), new ArrayList<>());
        medicalCenter3 = new MedicalCenter(40199, "Leighton Hospital", "Leighton Hospital", "Middlewich Road", "", "Crewe", "Cheshire", "CW1 4QJ", new GeographicCoordinates(53.117691040039063, -2.4758484363555908), new ArrayList<>());
        medicalCenters = new ArrayList<>();
        medicalCenters.add(medicalCenter1);
        medicalCenters.add(medicalCenter2);
        medicalCenters.add(medicalCenter3);
        testPosition = new GeographicCoordinates(53.135489, -2.556205);
    }

    @Test
    void calculateTravelTimeBetweenTwoPointsTest() {
        long travelTime = travelTimeCalculator.calculateTravelTimeBetweenTwoPoints(testPosition, medicalCenter3.getGeographicCoordinates());
        // Estimated time by Google Maps = 12 minutes => Test if travel time is between 10 and 15 min
        assertTrue(600000 < travelTime);
        System.out.println(travelTime);
        assertTrue(travelTime < 900000);
        System.out.println(travelTime);
    }

    @Test
    void findClosestMedicalCenterTest() {
        MedicalCenterWithTravelTime medicalCenterWithTravelTime = travelTimeCalculator.findClosestMedicalCenter(testPosition, medicalCenters);
        assertEquals(medicalCenter3, medicalCenterWithTravelTime.getMedicalCenter());
    }

}