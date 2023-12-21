package com.medhead.emergency.entity;

public class MedicalCenter {

    public MedicalCenter() {
    }

    public MedicalCenter(int organisationIdP, String organisationNameP, String address1P, String address2P, String address3P, String cityP, String countyP, String postcodeP, String latitudeP, String longitudeP) {
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
    }

    private int organisationId;

    private String organisationName;

    private String address1;

    private String address2;

    private String address3;

    private String city;

    private String county;

    private String postcode;

    private String latitude;

    private String longitude;

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
