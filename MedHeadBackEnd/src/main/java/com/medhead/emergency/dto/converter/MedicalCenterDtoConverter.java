package com.medhead.emergency.dto.converter;

import com.medhead.emergency.dto.MedicalCenterDto;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;

public final class MedicalCenterDtoConverter {

     public static MedicalCenterDto convertMedicalCenterWithTravelTimeToMedicalCenterDto(MedicalCenterWithTravelTime medicalCenter) {
        MedicalCenterDto medicalCenterDto = new MedicalCenterDto();
        medicalCenterDto.setOrganisationName(medicalCenter.getMedicalCenter().getOrganisationName());
        medicalCenterDto.setAddress1(medicalCenter.getMedicalCenter().getAddress1());
        medicalCenterDto.setAddress2(medicalCenter.getMedicalCenter().getAddress2());
        medicalCenterDto.setAddress3(medicalCenter.getMedicalCenter().getAddress3());
        medicalCenterDto.setCity(medicalCenter.getMedicalCenter().getCity());
        medicalCenterDto.setCounty(medicalCenter.getMedicalCenter().getCounty());
        medicalCenterDto.setPostcode(medicalCenter.getMedicalCenter().getPostcode());
        medicalCenterDto.setSpecialities(medicalCenter.getMedicalCenter().getSpecialities());
        medicalCenterDto.setTravelTime(medicalCenter.getTravelTime());
        return medicalCenterDto;
    }
}
