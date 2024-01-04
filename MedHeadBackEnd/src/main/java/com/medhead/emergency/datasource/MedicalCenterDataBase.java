package com.medhead.emergency.datasource;

import com.medhead.emergency.entity.MedicalCenter;

import java.util.List;

/**
 * For POC usage only
 */
public final class MedicalCenterDataBase {

    private final List<MedicalCenter> medicalCenters;

    public MedicalCenterDataBase(List<MedicalCenter> medicalCentersP) {
        medicalCenters = medicalCentersP;
    }

    public List<MedicalCenter> getMedicalCenters() {
        return medicalCenters;
    }
}
