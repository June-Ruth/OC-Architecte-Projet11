package com.medhead.emergency.entity;

public class MedicalCenter {

    public MedicalCenter() {
    }

    public MedicalCenter(int organisationIdP, String organisationNameP, String address1P, String address2P, String address3P, String cityP, String countyP, String postcodeP, double latitudeP, double longitudeP, Speciality specialityP) {
        organisationId = organisationIdP;
        organisationName = organisationNameP;
        address1 = address1P;
        address2 = address2P;
        address3 = address3P;
        city = cityP;
        county = countyP;
        postcode = postcodeP;
        latitude = latitudeP;
        longitude = longitudeP;
        speciality = specialityP;
    }

    private int organisationId;

    private String organisationName;

    private String address1;

    private String address2;

    private String address3;

    private String city;

    private String county;

    private String postcode;

    private double latitude;

    private double longitude;

    private Speciality speciality;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitudeP) {
        latitude = latitudeP;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitudeP) {
        longitude = longitudeP;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality specialityP) {
        speciality = specialityP;
    }

}
