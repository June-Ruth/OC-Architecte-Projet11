package com.medhead.emergency.graphhopper;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.medhead.emergency.configuration.EnvironmentValues;
import org.apache.commons.io.FileUtils;

import java.io.*;

public enum GraphHopperManager {

    INSTANCE;

    private GraphHopper GRAPHHOPPER;

    GraphHopperManager() {
        setGraphHopperInstance();
    }

    public void setGraphHopperInstance() {
        GRAPHHOPPER = new GraphHopper();
        InputStream osmStream = getClass().getResourceAsStream(EnvironmentValues.getOsmFile());
        File copied = new File("copy.osm.pbf");
        try {
            FileUtils.copyInputStreamToFile(osmStream, copied);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GRAPHHOPPER.setOSMFile(copied.getAbsolutePath());
        GRAPHHOPPER.setGraphHopperLocation(EnvironmentValues.getGraphHopperTarget());
        GRAPHHOPPER.setProfiles(new Profile("car").setVehicle("car").setTurnCosts(false));
        GRAPHHOPPER.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
        GRAPHHOPPER.importOrLoad();
    }

    public GraphHopper getGraphHopperInstance() {
        return GRAPHHOPPER;
    }
}
