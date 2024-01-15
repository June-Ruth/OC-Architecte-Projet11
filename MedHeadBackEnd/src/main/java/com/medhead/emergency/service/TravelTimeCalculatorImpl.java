package com.medhead.emergency.service;

import com.google.common.collect.TreeMultimap;
import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class TravelTimeCalculatorImpl implements TravelTimeCalculator {

    public TravelTimeCalculatorImpl() {
    }

    private static final String URL = "https://graphhopper.com/api/1/";
    private static final String DockerURL = "http://localhost:8989/route?";

    private static final String KEY = "1a39e113-b490-46aa-bb5c-5142a3430c3d";

    /**
     * @inheritDoc
     */
    /*@Override
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
    }*/

    @Override
    public int calculateTravelTimeBetweenTwoPoints(GeographicCoordinates departure, GeographicCoordinates arrival) {
        try(HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(DockerURL + "point=" + departure.getLatitude() + "," + departure.getLongitude()
                            + "&point=" + arrival.getLatitude() + "," + arrival.getLongitude() + "&profile=car"))
                    .GET()
                    .build();

            System.out.println(request);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            int time = new JSONObject(response.body()).getJSONArray("paths").getJSONObject(0).getInt("time");
            return time;
        } catch (JSONException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
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
                int time = calculateTravelTimeBetweenTwoPoints(position, medicalCenter.getGeographicCoordinates());
                closestMedicalCenters.put(time, medicalCenter);
            }
        }
        Map.Entry<Integer, MedicalCenter> closestMedicalCenter = closestMedicalCenters.entries().stream().findFirst().get();

        return new MedicalCenterWithTravelTime(closestMedicalCenter.getValue(), closestMedicalCenter.getKey());
    }
}
