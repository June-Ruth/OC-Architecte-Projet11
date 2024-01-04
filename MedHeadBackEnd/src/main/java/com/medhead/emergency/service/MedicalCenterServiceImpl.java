package com.medhead.emergency.service;

import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.Speciality;
import com.medhead.emergency.repository.MedicalCenterSpecialismsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalCenterServiceImpl implements MedicalCenterService {

    private final MedicalCenterSpecialismsRepository medicalCenterSpecialismsRepository;

    public MedicalCenterServiceImpl(MedicalCenterSpecialismsRepository medicalCenterSpecialismsRepositoryP) {
        medicalCenterSpecialismsRepository = medicalCenterSpecialismsRepositoryP;
    }

    @Override
    public List<MedicalCenter> getAllMedicalCentersBySpeciality(Speciality speciality) {
        return medicalCenterSpecialismsRepository.findAllMedicalCentersBySpeciality(speciality);
    }
}
