package com.medhead.emergency.controller;

import com.medhead.emergency.dto.MedicalCenterDto;
import com.medhead.emergency.dto.converter.MedicalCenterDtoConverter;
import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;
import com.medhead.emergency.entity.Speciality;
import com.medhead.emergency.service.BedAvailabilityService;
import com.medhead.emergency.service.MedicalCenterService;
import com.medhead.emergency.service.TravelTimeCalculator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmergencyController {

    private MedicalCenterService medicalCenterService;

    private BedAvailabilityService bedAvailabilityService;

    private TravelTimeCalculator travelTimeCalculator;


    public EmergencyController(
            MedicalCenterService medicalCenterServiceP,
            BedAvailabilityService bedAvailabilityServiceP,
            TravelTimeCalculator travelTimeCalculatorP
    ) {
        medicalCenterService = medicalCenterServiceP;
        bedAvailabilityService = bedAvailabilityServiceP;
        travelTimeCalculator = travelTimeCalculatorP;

    }


    @GetMapping("/emergency/hospital")
    @ResponseBody
    public MedicalCenterDto getMedicalCenterBySpecialityAndLocalisation(
            @RequestParam("speciality") Speciality speciality,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude
    ) {
        List<MedicalCenter> medicalCentersBySpeciality = medicalCenterService.getAllMedicalCentersBySpeciality(speciality);
        List<MedicalCenter>  medicalCentersBySpecialityWithAvailability= medicalCentersBySpeciality.parallelStream()
                .filter(medicalCenter -> bedAvailabilityService.isBedAvailable(medicalCenter.getOrganisationId()))
                .toList();

        MedicalCenterWithTravelTime closestMedicalCenter = travelTimeCalculator
                .findClosestMedicalCenter(new GeographicCoordinates(latitude, longitude), medicalCentersBySpecialityWithAvailability);

        return MedicalCenterDtoConverter.convertMedicalCenterWithTravelTimeToMedicalCenterDto(closestMedicalCenter);
    }

}
