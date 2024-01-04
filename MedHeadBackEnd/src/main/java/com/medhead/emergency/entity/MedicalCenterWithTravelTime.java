package com.medhead.emergency.entity;

public class MedicalCenterWithTravelTime {

    public MedicalCenterWithTravelTime(MedicalCenter medicalCenterP, int travelTimeP) {
        medicalCenter = medicalCenterP;
        travelTime = travelTimeP;
    }

    MedicalCenter medicalCenter;

    int travelTime;

    public MedicalCenter getMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(MedicalCenter medicalCenter) {
        this.medicalCenter = medicalCenter;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
}
