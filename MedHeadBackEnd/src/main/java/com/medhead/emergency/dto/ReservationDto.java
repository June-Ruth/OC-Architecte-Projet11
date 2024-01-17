package com.medhead.emergency.dto;

import com.medhead.emergency.entity.Speciality;

import java.util.List;

public class ReservationDto {

    public ReservationDto(
            String organisationNameP,
            String address1P,
            String address2P,
            String address3P,
            String cityP,
            String countyP,
            String postcodeP,
            List<Speciality> specialitiesP,
            long travelTimeP,
            int reservationNumberP
    ) {
        organisationName = organisationNameP;
        address1 = address1P;
        address2 = address2P;
        address3 = address3P;
        city = cityP;
        county = countyP;
        postcode = postcodeP;
        specialities = specialitiesP;
        travelTime = travelTimeP;
        reservationNumber = reservationNumberP;
    }

    public ReservationDto() {
    }

    private String organisationName;

    private String address1;

    private String address2;

    private String address3;

    private String city;

    private String county;

    private String postcode;

    private List<Speciality> specialities;

    private long travelTime;

    private int reservationNumber;

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationNameP) {
        organisationName = organisationNameP;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1P) {
        address1 = address1P;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2P) {
        address2 = address2P;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3P) {
        address3 = address3P;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String cityP) {
        city = cityP;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String countyP) {
        county = countyP;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcodeP) {
        postcode = postcodeP;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialitiesP) {
        specialities = specialitiesP;
    }

    public long getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(long travelTimeP) {
        travelTime = travelTimeP;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumberP) {
        reservationNumber = reservationNumberP;
    }
}
