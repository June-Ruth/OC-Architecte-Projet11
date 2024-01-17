package com.medhead.emergency.entity;

public class MedicalCenterWithTravelTime {

    public MedicalCenterWithTravelTime(MedicalCenter medicalCenterP, long travelTimeP) {
        medicalCenter = medicalCenterP;
        travelTime = travelTimeP;
    }

    MedicalCenter medicalCenter;

    long travelTime;

    public MedicalCenter getMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(MedicalCenter medicalCenterP) {
        medicalCenter = medicalCenterP;
    }

    public long getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(long travelTimeP) {
        travelTime = travelTimeP;
    }
}
