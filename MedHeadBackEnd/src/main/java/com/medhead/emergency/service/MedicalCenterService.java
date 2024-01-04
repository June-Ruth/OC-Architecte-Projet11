package com.medhead.emergency.service;

import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.Speciality;

import java.util.List;

public interface MedicalCenterService {

    /**
     * Get all the medical centers that provided a specific speciality.
     * @param speciality searched
     * @return list of all medical centers that provided the given speciality
     */
    List<MedicalCenter> getAllMedicalCentersBySpeciality(Speciality speciality);
}
