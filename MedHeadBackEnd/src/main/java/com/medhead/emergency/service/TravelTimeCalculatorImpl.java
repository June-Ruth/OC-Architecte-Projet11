package com.medhead.emergency.service;

import com.google.common.collect.TreeMultimap;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class TravelTimeCalculatorImpl implements TravelTimeCalculator {

    public TravelTimeCalculatorImpl() {
    }

    private static final String ENGLAND_FILE = "src/main/resources/static/england-latest.osm.pbf";
    static GraphHopper getGraphHopperInstance() {
        GraphHopper graphHopper = new GraphHopper();
        graphHopper.setOSMFile(ENGLAND_FILE);
        graphHopper.setGraphHopperLocation("target/routing-graph-cache");
        graphHopper.setProfiles(new Profile("car").setVehicle("car").setTurnCosts(false));
        graphHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
        graphHopper.importOrLoad();
        return graphHopper;
    }

    @Override
    public long calculateTravelTimeBetweenTwoPoints(GeographicCoordinates departure, GeographicCoordinates arrival) {
        GraphHopper graphHopper = getGraphHopperInstance();
        try {
            GHRequest request = new GHRequest(departure.getLatitude(), departure.getLongitude(), arrival.getLatitude(), arrival.getLongitude())
                    .setProfile("car")
                    .setLocale(Locale.US);
            GHResponse response = graphHopper.route(request);
            return response.getBest().getTime();
        } finally {
            graphHopper.close();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public MedicalCenterWithTravelTime findClosestMedicalCenter(GeographicCoordinates position, List<MedicalCenter> medicalCenters) {
        TreeMultimap<Integer, MedicalCenter> closestMedicalCenters = TreeMultimap.create();
        for(MedicalCenter medicalCenter : medicalCenters) {
            if(medicalCenter.getGeographicCoordinates().getLongitude() != 0 && medicalCenter.getGeographicCoordinates().getLatitude() != 0) {
                long time = calculateTravelTimeBetweenTwoPoints(position, medicalCenter.getGeographicCoordinates());
                closestMedicalCenters.put((int) time, medicalCenter);
            }
        }
        Map.Entry<Integer, MedicalCenter> closestMedicalCenter = closestMedicalCenters.entries().stream().findFirst().get();

        return new MedicalCenterWithTravelTime(closestMedicalCenter.getValue(), closestMedicalCenter.getKey());
    }
}
