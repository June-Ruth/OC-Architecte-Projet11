package com.medhead.emergency.service;

import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.Speciality;
import com.medhead.emergency.repository.MedicalCenterSpecialismsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MedicalCenterServiceTest {

    @Mock
    private MedicalCenterSpecialismsRepository medicalCenterSpecialismsRepository;

    private MedicalCenterService medicalCenterService;

    private List<MedicalCenter> mockMedicalCenters;
    private MedicalCenter mockMedicalCenter1;
    private MedicalCenter mockMedicalCenter2;
    private MedicalCenter mockMedicalCenter3;

    @BeforeEach
    void beforeEach() {
        medicalCenterService = new MedicalCenterServiceImpl(medicalCenterSpecialismsRepository);
        mockMedicalCenters = new ArrayList<>();
        mockMedicalCenter1 = new MedicalCenter(1, "Center 1", "Address 11", "Address 21", "Address 31", "City 1", "County 1", "PostCode 1", new GeographicCoordinates(11.11,11.11), List.of(Speciality.ALLERGY, Speciality.EMERGENCY_MEDICINE));
        mockMedicalCenter2 = new MedicalCenter(2, "Center 2", "Address 12", "Address 22", "Address 32", "City 2", "County 2", "PostCode 2", new GeographicCoordinates(22.22,22.22), List.of(Speciality.ALLERGY, Speciality.CARDIOLOGY));
        mockMedicalCenter3 = new MedicalCenter(3, "Center 3", "Address 13", "Address 23", "Address 33", "City 3", "County 3", "PostCode 3", new GeographicCoordinates(33.33,33.33), List.of(Speciality.ALLERGY, Speciality.NUCLEAR_MEDICINE));
        mockMedicalCenters.add(mockMedicalCenter1);
        mockMedicalCenters.add(mockMedicalCenter2);
        mockMedicalCenters.add(mockMedicalCenter3);
    }

    @Test
    void getAllMedicalCentersBySpecialityThatExistsTest() {
        when(medicalCenterSpecialismsRepository.findAllMedicalCentersBySpeciality(any())).thenReturn(mockMedicalCenters);
        medicalCenterService.getAllMedicalCentersBySpeciality(Speciality.ALLERGY);
        verify(medicalCenterSpecialismsRepository, times(1)).findAllMedicalCentersBySpeciality(Speciality.ALLERGY);
    }
}
