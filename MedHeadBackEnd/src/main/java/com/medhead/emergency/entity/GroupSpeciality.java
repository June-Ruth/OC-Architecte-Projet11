package com.medhead.emergency.entity;

public enum GroupSpeciality {

    ANAESTHETICS("Anaesthetics"),
    CLINICAL_ONCOLOGY("Clinical oncology"),
    DENTAL_GROUP("Dental group"),
    EMERGENCY_MEDICINE("Emergency Medicine"),
    GENERAL_MEDICINE_GROUP("General medicine group"),
    OBSTETRICS_AND_GYNECOLOGY("Obstetrics & gynecology"),
    PAEDIATRIC_GROUP("Paediatric group"),
    PATHOLOGY_GROUP("Pathology group"),
    PHM_AND_CHS_GROUP("PHM & CHS group"),
    PSYCHIATRY_GROUP("Psychiatry group"),
    RADIOLOGY_GROUP("Radiology group"),
    SURGICAL_GROUP("Surgical group");

    public final String label;

    private GroupSpeciality(String labelP) {
        label = labelP;
    }
}
