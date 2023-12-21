package com.medhead.emergency.entity;

public enum GroupSpeciality {

    ANAESTHETICS("Anaesthetics"),
    CLINICAL_ONCOLOGY("Clinical oncology"),
    DENTAL("Dental group"),
    EMERGENCY_MEDICINE("Emergency medicine"),
    GENERAL_MEDICINE("General medicine group"),
    OBSTETRICS_GYNECOLOGY("Obstetrics & gynecology"),
    PAEDIATRIC("Paediatric group"),
    PATHOLOGY("Pathology group"),
    PHM_CHS("PHM & CHS group"),
    PSYCHIATRY("Psychiatry group"),
    RADIOLOGY("Radiology group"),
    SURGICAL("Surgical group");

    public final String label;

    private GroupSpeciality(String labelP) {
        label = labelP;
    }
}
