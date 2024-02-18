package com.medhead.emergency.controller;

import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;
import com.medhead.emergency.entity.Speciality;
import com.medhead.emergency.service.BedAvailabilityService;
import com.medhead.emergency.service.MedicalCenterService;
import com.medhead.emergency.service.TravelTimeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmergencyController.class)
public class EmergencyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicalCenterService medicalCenterService;

    @MockBean
    private BedAvailabilityService bedAvailabilityService;

    @MockBean
    private TravelTimeCalculator travelTimeCalculator;


    private List<MedicalCenter> mockMedicalCenters;
    private MedicalCenter mockMedicalCenter1;
    private MedicalCenter mockMedicalCenter2;
    private MedicalCenter mockMedicalCenter3;
    private GeographicCoordinates testPosition;

    @BeforeEach
    void beforeEach() {
        mockMedicalCenters = new ArrayList<>();
        mockMedicalCenter1 = new MedicalCenter(1, "Center 1", "Address 11", "Address 21", "Address 31", "City 1", "County 1", "PostCode 1", new GeographicCoordinates(11.11,11.11), List.of(Speciality.ALLERGY, Speciality.EMERGENCY_MEDICINE));
        mockMedicalCenter2 = new MedicalCenter(2, "Center 2", "Address 12", "Address 22", "Address 32", "City 2", "County 2", "PostCode 2", new GeographicCoordinates(22.22,22.22), List.of(Speciality.ALLERGY, Speciality.CARDIOLOGY));
        mockMedicalCenter3 = new MedicalCenter(3, "Center 3", "Address 13", "Address 23", "Address 33", "City 3", "County 3", "PostCode 3", new GeographicCoordinates(33.33,33.33), List.of(Speciality.ALLERGY, Speciality.NUCLEAR_MEDICINE));
        mockMedicalCenters.add(mockMedicalCenter1);
        mockMedicalCenters.add(mockMedicalCenter2);
        mockMedicalCenters.add(mockMedicalCenter3);
        testPosition = new GeographicCoordinates(42.563588, 1.591132);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getMedicalCenterBySpecialityAndLocalisationAuthenticatedUserTest() throws Exception {
        when(medicalCenterService.getAllMedicalCentersBySpeciality(any())).thenReturn(mockMedicalCenters);
        when(bedAvailabilityService.isBedAvailable(anyInt())).thenReturn(true);
        when(travelTimeCalculator.findClosestMedicalCenter(any(), any())).thenReturn(new MedicalCenterWithTravelTime(mockMedicalCenter1, 27));
        when(bedAvailabilityService.registerOneBedReservation(anyInt())).thenReturn(42);
        mvc.perform(get("/emergency/hospital")
                .queryParam("speciality", "ALLERGY")
                .queryParam("latitude", String.valueOf(testPosition.getLatitude()))
                .queryParam("longitude", String.valueOf(testPosition.getLongitude())))
                .andExpect(status().isOk());
    }
}
