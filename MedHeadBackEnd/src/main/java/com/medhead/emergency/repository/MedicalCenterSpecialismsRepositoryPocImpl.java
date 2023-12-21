package com.medhead.emergency.repository;

import com.medhead.emergency.entity.MedicalCenter;

import java.util.List;

/**
 * This implementation is dedicated for POC usage only.
 * The production implementation should call directly the API for medical center specialisms.
 */
public class MedicalCenterSpecialismsRepositoryPocImpl implements MedicalCenterSpecialismsRepository {

    /**
     * @inheritDoc
     */
    @Override
    public MedicalCenter findMedicalCenterById(int organisationId) {
        //TODO
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<MedicalCenter> findAllMedicalCenters() {
        //TODO
        return null;
    }
}
