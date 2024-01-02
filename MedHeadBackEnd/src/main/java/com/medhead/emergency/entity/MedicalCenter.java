package com.medhead.emergency.entity;

import java.util.List;

public class MedicalCenter implements Comparable<MedicalCenter>{

    public MedicalCenter() {
    }

    public MedicalCenter(int organisationIdP, String organisationNameP, String address1P, String address2P, String address3P, String cityP, String countyP, String postcodeP, GeographicCoordinates geographicCoordinatesP, List<Speciality> specialitiesP) {
        organisationId = organisationIdP;
        organisationName = organisationNameP;
        address1 = address1P;
        address2 = address2P;
        address3 = address3P;
        city = cityP;
        county = countyP;
        postcode = postcodeP;
        geographicCoordinates = geographicCoordinatesP;
        specialities = specialitiesP;
    }

    private int organisationId;

    private String organisationName;

    private String address1;

    private String address2;

    private String address3;

    private String city;

    private String county;

    private String postcode;

    GeographicCoordinates geographicCoordinates;

    private List<Speciality> specialities;

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationIdP) {
        organisationId = organisationIdP;
    }

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

    public GeographicCoordinates getGeographicCoordinates() {
        return geographicCoordinates;
    }

    public void setGeographicCoordinates(GeographicCoordinates geographicCoordinatesP) {
        geographicCoordinates = geographicCoordinatesP;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialityP) {
        specialities = specialityP;
    }

    @Override
    public int compareTo(MedicalCenter medicalCenter) {
        return Integer.compare(organisationId, medicalCenter.getOrganisationId());
    }
}
