package com.medhead.emergency.graphhopper;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.medhead.emergency.configuration.EnvironmentValues;

public enum GraphHopperManager {

    INSTANCE;

    private GraphHopper GRAPHHOPPER;

    GraphHopperManager() {
        getGraphHopperInstance();
    }

    public GraphHopper getGraphHopperInstance() {
        GRAPHHOPPER = new GraphHopper();
        GRAPHHOPPER.setOSMFile(EnvironmentValues.getOsmFile());
        GRAPHHOPPER.setGraphHopperLocation(EnvironmentValues.getGraphHopperTarget());
        GRAPHHOPPER.setProfiles(new Profile("car").setVehicle("car").setTurnCosts(false));
        GRAPHHOPPER.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
        GRAPHHOPPER.importOrLoad();
        return GRAPHHOPPER;
    }
}
