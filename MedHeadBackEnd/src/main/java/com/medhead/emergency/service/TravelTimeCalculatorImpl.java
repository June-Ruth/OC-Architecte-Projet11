package com.medhead.emergency.service;

import com.google.common.collect.TreeMultimap;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;
import com.medhead.emergency.graphhopper.GraphHopperManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TravelTimeCalculatorImpl implements TravelTimeCalculator {

    public TravelTimeCalculatorImpl() {
    }

    @Override
    public long calculateTravelTimeBetweenTwoPoints(GeographicCoordinates departure, GeographicCoordinates arrival) {
        GraphHopper graphHopper = GraphHopperManager.INSTANCE.getGraphHopperInstance();
        GHRequest request = new GHRequest(departure.getLatitude(), departure.getLongitude(), arrival.getLatitude(), arrival.getLongitude())
                .setProfile("car")
                .setLocale(Locale.US);
        GHResponse response = graphHopper.route(request);
        return response.getBest().getTime();
    }

    /**
     * @inheritDoc
     */
    @Override
    public MedicalCenterWithTravelTime findClosestMedicalCenter(GeographicCoordinates position, List<MedicalCenter> medicalCenters) {
        TreeMultimap<Integer, MedicalCenter> closestMedicalCenters = TreeMultimap.create();

        Stream<MedicalCenter> medicalCenterStream = StreamSupport.stream(((Iterable<MedicalCenter>) medicalCenters).spliterator(), true);
        medicalCenterStream.forEach(medicalCenter -> {
            long time = calculateTravelTimeBetweenTwoPoints(position, medicalCenter.getGeographicCoordinates());
            closestMedicalCenters.put((int) time, medicalCenter);
        });

        Map.Entry<Integer, MedicalCenter> closestMedicalCenter = closestMedicalCenters.entries().stream().findFirst().get();

        return new MedicalCenterWithTravelTime(closestMedicalCenter.getValue(), closestMedicalCenter.getKey());
    }
}
