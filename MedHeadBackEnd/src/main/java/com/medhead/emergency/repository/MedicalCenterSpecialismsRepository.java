package com.medhead.emergency.repository;

import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.Speciality;

import java.util.List;

public interface MedicalCenterSpecialismsRepository {

    /**
     * Get the medical center that corresponds to the organisation ID.
     * @param organisationId of the medical center
     * @return Medical Center or null if it doesn't exist
     */
    MedicalCenter findMedicalCenterById(int organisationId);

    /**
     * Get all the medical centers referenced in NHS system.
     * @return list of all the medical centers
     */
    List<MedicalCenter> findAllMedicalCenters();

    /**
     * Get all the medical centers that provided a specific speciality.
     * @param speciality searched
     * @return list of all medical centers that provided the given speciality
     */
    List<MedicalCenter> findAllMedicalCentersBySpeciality(Speciality speciality);

}
