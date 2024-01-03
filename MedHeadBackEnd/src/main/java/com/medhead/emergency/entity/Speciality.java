package com.medhead.emergency.entity;

import java.util.Random;

public enum Speciality {

    // Anaesthetics
    ANAESTHETICS("Anaesthetics", GroupSpeciality.ANAESTHETICS),
    INTENSIVE_CARE_MEDICINE("Intensive care medicine", GroupSpeciality.ANAESTHETICS),

    // Clinical oncology
    CLINICAL_ONCOLOGY("Clinical oncology", GroupSpeciality.CLINICAL_ONCOLOGY),

    // Dental group
    ADDITIONAL_DENTAL_SPECIALTIES("Additional dental specialties", GroupSpeciality.DENTAL_GROUP),
    DENTAL_AND_MAXILLOFACIAL_RADIOLOGY("Dental and maxillofacial radiology", GroupSpeciality.DENTAL_GROUP),
    ENDODONTICS("Endodontics", GroupSpeciality.DENTAL_GROUP),
    ORAL_AND_MAXILLOFACIAL_SURGERY("Oral and maxillofacial surgery", GroupSpeciality.DENTAL_GROUP),
    ORAL_AND_MAXILLOFACIAL_PATHOLOGY("Oral and maxillofacial pathology", GroupSpeciality.DENTAL_GROUP),
    ORAL_MEDICINE("Oral medicine", GroupSpeciality.DENTAL_GROUP),
    ORAL_SURGERY("Oral surgery", GroupSpeciality.DENTAL_GROUP),
    ORTHODONTICS("Orthodontics", GroupSpeciality.DENTAL_GROUP),
    PAEDIATRIC_DENTISTRY("Paediatric dentistry", GroupSpeciality.DENTAL_GROUP),
    PERIODONTICS("Periodontics", GroupSpeciality.DENTAL_GROUP),
    PROSTHODONTICS("Prosthodontics", GroupSpeciality.DENTAL_GROUP),
    RESTORATIVE_DENTISTRY("Restorative dentistry", GroupSpeciality.DENTAL_GROUP),
    SPECIAL_CARE_DENTISTRY("Special care dentistry", GroupSpeciality.DENTAL_GROUP),

    // Emergency Medicine
    EMERGENCY_MEDICINE("Emergency medicine", GroupSpeciality.EMERGENCY_MEDICINE),

    // General medicine group
    ACUTE_INTERNAL_MEDICINE("Acute internal medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    ALLERGY("Allergy", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    AUDIO_VESTIBULAR_MEDICINE("Audio vestibular medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    CARDIOLOGY("Cardiology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    CLINICAL_GENETICS("Clinical genetics", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    CLINICAL_NEUROPHYSIOLOGY("Clinical neurophysiology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    CLINICAL_PHARMACOLOGY_AND_THERAPEUTICS("Clinical pharmacology and therapeutics", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    DERMATOLOGY("Dermatology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    ENDOCRINOLOGY_AND_DIABETES_MELLITUS("Endocrinology and diabetes mellitus", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    GASTROENTEROLOGY("Gastroenterology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    GENERAL_INTERNAL_MEDICINE("General (internal) medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    GENERAL_MEDICAL_PRACTITIONER("General med practitioner", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    GENERAL_PRACTICE_6_MONTH_TRAINING("General practice (GP) 6-month training", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    GENITO_URINARY_MEDICINE("Genito-urinary medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    GERIATRIC_MEDICINE("Geriatric medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    INFECTIOUS_DISEASES("Infectious diseases", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    MEDICAL_ONCOLOGY("Medical oncology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    MEDICAL_OPHTHALMOLOGY("Medical ophthalmology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    NEUROLOGY("Neurology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    OCCUPATIONAL_MEDICINE("Occupational medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    OTHER("Other", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    PALLIATIVE_MEDICINE("Palliative medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    REHABILITATION_MEDICINE("Rehabilitation medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    RENAL_MEDICINE("Renal medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    RESPIRATORY_MEDICINE("Respiratory medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    RHEUMATOLOGY("Rheumatology", GroupSpeciality.GENERAL_MEDICINE_GROUP),
    SPORT_AND_EXERCISE_MEDICINE("Sport and exercise medicine", GroupSpeciality.GENERAL_MEDICINE_GROUP),

    // Obstetrics & gynecology
    COMMUNITY_SEXUAL_AND_REPRODUCTIVE_HEALTH("Community sexual and reproductive health", GroupSpeciality.OBSTETRICS_AND_GYNECOLOGY),

    // Paediatric group
    PAEDIATRIC_CARDIOLOGY("Paediatric cardiology", GroupSpeciality.PAEDIATRIC_GROUP),
    PAEDIATRICS("Paediatrics", GroupSpeciality.PAEDIATRIC_GROUP),

    // Pathology group
    CHEMICAL_PATHOLOGY("Chemical pathology", GroupSpeciality.PATHOLOGY_GROUP),
    DIAGNOSTIC_NEUROPATHOLOGY("Diagnostic neuropathology", GroupSpeciality.PATHOLOGY_GROUP),
    FORENSIC_HISTOPATHOLOGY("Forensic histopathology", GroupSpeciality.PATHOLOGY_GROUP),
    GENERAL_PATHOLOGY("General pathology", GroupSpeciality.PATHOLOGY_GROUP),
    HAEMATOLOGY("Haematology", GroupSpeciality.PATHOLOGY_GROUP),
    HISTOPATHOLOGY("Histopathology", GroupSpeciality.PATHOLOGY_GROUP),
    IMMUNOLOGY("Immunology", GroupSpeciality.PATHOLOGY_GROUP),
    MEDICAL_MICROBIOLOGY("Medical microbiology", GroupSpeciality.PATHOLOGY_GROUP),
    PAEDIATRIC_AND_PERINATAL_PATHOLOGY("Paediatric and perinatal pathology", GroupSpeciality.PATHOLOGY_GROUP),
    VIROLOGY("Virology", GroupSpeciality.PATHOLOGY_GROUP),

    // PHM & CHS group
    COMMUNITY_HEALTH_SERVICE_DENTAL("Community health service dental", GroupSpeciality.PHM_AND_CHS_GROUP),
    COMMUNITY_HEALTH_SERVICE_MEDICAL("Community health service medical", GroupSpeciality.PHM_AND_CHS_GROUP),
    DENTAL_PUBLIC_HEALTH("Dental public health", GroupSpeciality.PHM_AND_CHS_GROUP),
    GENERAL_DENTAL_PRACTITIONER("General dental practitioner", GroupSpeciality.PHM_AND_CHS_GROUP),
    PUBLIC_HEALTH_MEDICINE("Public health medicine", GroupSpeciality.PHM_AND_CHS_GROUP),

    // Psychiatry group
    CHILD_AND_ADOLESCENT_PSYCHIATRY("Child and adolescent psychiatry", GroupSpeciality.PSYCHIATRY_GROUP),
    FORENSIC_PSYCHIATRY("Forensic psychiatry", GroupSpeciality.PSYCHIATRY_GROUP),
    GENERAL_PSYCHIATRY("General psychiatry", GroupSpeciality.PSYCHIATRY_GROUP),
    OLD_AGE_PSYCHIATRY("Old age psychiatry", GroupSpeciality.PSYCHIATRY_GROUP),
    PSYCHIATRY_OF_LEARNING_DISABILITY("Psychiatry of learning disability", GroupSpeciality.PSYCHIATRY_GROUP),
    PSYCHOTHERAPY("Psychotherapy", GroupSpeciality.PSYCHIATRY_GROUP),

    // Radiology group
    CLINICAL_RADIOLOGY("Clinical radiology", GroupSpeciality.RADIOLOGY_GROUP),
    NUCLEAR_MEDICINE("Nuclear medicine", GroupSpeciality.RADIOLOGY_GROUP),

    // Surgical group
    CARDIOTHORACIC_SURGERY("Cardiothoracic surgery", GroupSpeciality.SURGICAL_GROUP),
    GENERAL_SURGERY("General surgery", GroupSpeciality.SURGICAL_GROUP),
    NEUROSURGERY("Neurosurgery", GroupSpeciality.SURGICAL_GROUP),
    OPHTHALMOLOGY("Ophthalmology", GroupSpeciality.SURGICAL_GROUP),
    OTOLARYNGOLOGY("Otolaryngology", GroupSpeciality.SURGICAL_GROUP),
    PAEDIATRIC_SURGERY("Paediatric surgery", GroupSpeciality.SURGICAL_GROUP),
    PLASTIC_SURGERY("Plastic surgery", GroupSpeciality.SURGICAL_GROUP),
    TRAUMA_AND_ORTHOPEDIC_SURGERY("Trauma and orthopedic surgery", GroupSpeciality.SURGICAL_GROUP),
    UROLOGY("Urology", GroupSpeciality.SURGICAL_GROUP),
    VASCULAR_SURGERY("Vascular Surgery", GroupSpeciality.SURGICAL_GROUP);

    private static final Random RANDOM = new Random();

    public static Speciality randomSpeciality() {
        Speciality[] specialities = values();
        return specialities[RANDOM.nextInt(specialities.length)];
    }

    public final String label;

    public final GroupSpeciality groupSpeciality;

    private Speciality(String labelP, GroupSpeciality groupSpecialityP) {
        label = labelP;
        groupSpeciality = groupSpecialityP;

    }
}
