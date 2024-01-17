package com.medhead.emergency.dto.converter;

import com.medhead.emergency.dto.ReservationDto;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;

public final class ReservationDtoConverter {

     public static ReservationDto convertMedicalCenterWithTravelTimeToReservationDto(MedicalCenterWithTravelTime medicalCenter, int reservationNumber) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setOrganisationName(medicalCenter.getMedicalCenter().getOrganisationName());
        reservationDto.setAddress1(medicalCenter.getMedicalCenter().getAddress1());
        reservationDto.setAddress2(medicalCenter.getMedicalCenter().getAddress2());
        reservationDto.setAddress3(medicalCenter.getMedicalCenter().getAddress3());
        reservationDto.setCity(medicalCenter.getMedicalCenter().getCity());
        reservationDto.setCounty(medicalCenter.getMedicalCenter().getCounty());
        reservationDto.setPostcode(medicalCenter.getMedicalCenter().getPostcode());
        reservationDto.setSpecialities(medicalCenter.getMedicalCenter().getSpecialities());
        reservationDto.setTravelTime(medicalCenter.getTravelTime());
        reservationDto.setReservationNumber(reservationNumber);
        return reservationDto;
    }
}
