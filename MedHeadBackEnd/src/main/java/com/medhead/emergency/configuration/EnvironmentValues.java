package com.medhead.emergency.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentValues {

    public EnvironmentValues(
            @Value("${data.csv.file}") String hospitalFileUrlP,
            @Value("${osm.file}") String osmFileP,
            @Value("${graphhopper.target}") String graphHopperTargetP) {
        hospitalFileUrl = hospitalFileUrlP;
        osmFile = osmFileP;
        graphHopperTarget = graphHopperTargetP;
    }

    private static String hospitalFileUrl;

    private static String osmFile;

    private static String graphHopperTarget;

    public static String getHospitalFileUrl() {
        return hospitalFileUrl;
    }

    public static String getOsmFile() {
        return osmFile;
    }

    public static String getGraphHopperTarget() {
        return graphHopperTarget;
    }
}
