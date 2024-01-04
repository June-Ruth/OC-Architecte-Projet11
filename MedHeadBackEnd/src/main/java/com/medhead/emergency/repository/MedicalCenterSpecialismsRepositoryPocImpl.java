package com.medhead.emergency.repository;

import com.medhead.emergency.datasource.MedicalCenterDataBase;
import com.medhead.emergency.datasource.MedicalCenterDataBaseManager;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.Speciality;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * This implementation is dedicated for POC usage only.
 * The production implementation should call directly the API for medical center specialisms.
 */
@Repository
public class MedicalCenterSpecialismsRepositoryPocImpl implements MedicalCenterSpecialismsRepository {

    private final MedicalCenterDataBase MEDICAL_CENTER_DB = MedicalCenterDataBaseManager.INSTANCE.getMEDICAL_CENTER_DB();

    public MedicalCenterSpecialismsRepositoryPocImpl() throws IOException {
    }

    /**
     * @inheritDoc
     */
    @Override
    public MedicalCenter findMedicalCenterById(int organisationId) {
        return findAllMedicalCenters().stream()
                .filter((medicalCenter1 -> organisationId == medicalCenter1.getOrganisationId()))
                .findAny()
                .orElse(null);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<MedicalCenter> findAllMedicalCenters() {
        return MEDICAL_CENTER_DB.getMedicalCenters();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<MedicalCenter> findAllMedicalCentersBySpeciality(Speciality speciality) {
        return findAllMedicalCenters().stream()
                .filter(medicalCenter -> medicalCenter.getSpecialities().contains(speciality))
                .toList();
    }
}
