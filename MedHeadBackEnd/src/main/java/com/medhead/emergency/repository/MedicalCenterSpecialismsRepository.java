package com.medhead.emergency.repository;

import com.medhead.emergency.entity.MedicalCenter;

import java.util.List;

public interface MedicalCenterSpecialismsRepository {

    /**
     * Get the medical center that corresponds to the organisation ID
     * @param organisationId of the medical center
     * @return Medical Center or null if it doesn't exist
     */
    MedicalCenter findMedicalCenterById(int organisationId);

    /**
     * Get all the medical centers referenced in NHS system.
     * @return list of all the medical centers
     */
    List<MedicalCenter> findAllMedicalCenters();


}
