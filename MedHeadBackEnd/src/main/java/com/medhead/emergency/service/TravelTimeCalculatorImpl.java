package com.medhead.emergency.service;

import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class TravelTimeCalculatorImpl implements TravelTimeCalculator {

    public TravelTimeCalculatorImpl() {
    }

    private static final String URL = "https://graphhopper.com/api/1/";

    private static final String KEY = "1a39e113-b490-46aa-bb5c-5142a3430c3d";

    /**
     * @inheritDoc
     */
    @Override
    public int calculateTravelTimeBetweenTwoPoints(GeographicCoordinates departure, GeographicCoordinates arrival) {
        try(HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + "matrix?from_point=" + departure.getLatitude() + "," + departure.getLongitude()
                            + "&to_point=" + arrival.getLatitude() + "," + arrival.getLongitude()
                            + "&type=json&profile=car&out_array=times&key=" + KEY))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int time = new JSONObject(response.body()).getJSONArray("times").getJSONArray(0).getInt(0);
            return time;
        } catch (JSONException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public MedicalCenter findClosestMedicalCenter(GeographicCoordinates position, List<MedicalCenter> medicalCenters) {
        TreeMap<Integer, MedicalCenter> closestMedicalCenters = new TreeMap<>();
        for(MedicalCenter medicalCenter : medicalCenters) {
            int time = calculateTravelTimeBetweenTwoPoints(position, medicalCenter.getGeographicCoordinates());
            closestMedicalCenters.put(time, medicalCenter);
        }
        return closestMedicalCenters.firstEntry().getValue();
    }
}
