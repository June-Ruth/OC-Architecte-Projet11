package com.medhead.emergency.service;

import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.MedicalCenterWithTravelTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class TravelTimeCalculatorTest {

    @Container
    private static final MySQLContainer<?> SQL_CONTAINER = new MySQLContainer<>().withDatabaseName("medhead");

    @DynamicPropertySource
    static void configureMysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.driver-class-name", SQL_CONTAINER::getDriverClassName);
        registry.add("spring.datasource.url", SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", SQL_CONTAINER::getPassword);
    }

    private static TravelTimeCalculator travelTimeCalculator;

    private List<MedicalCenter> medicalCenters;
    private MedicalCenter medicalCenter1;
    private MedicalCenter medicalCenter2;
    private MedicalCenter medicalCenter3;
    private GeographicCoordinates testPosition;

    @BeforeEach
    void beforeEach() {
        travelTimeCalculator = new TravelTimeCalculatorImpl();
        medicalCenter1 = new MedicalCenter(17970, "Walton Community Hospital - Virgin Care Services Ltd", "", "Rodney Road", "", "Walton-on-Thames", "Surrey", "KT12 3LD", new GeographicCoordinates(42.471300, 1.493051), new ArrayList<>());
        medicalCenter2 = new MedicalCenter(18101, "North Somerset Community Partnership Cic HQ", "Castlewood", "Tickenham Road", "", "Clevedon", "Avon", "BS21 6AB", new GeographicCoordinates(42.633315, 1.499650), new ArrayList<>());
        medicalCenter3 = new MedicalCenter(40199, "Leighton Hospital", "Leighton Hospital", "Middlewich Road", "", "Crewe", "Cheshire", "CW1 4QJ", new GeographicCoordinates(42.553829, 1.427480), new ArrayList<>());
        medicalCenters = new ArrayList<>();
        medicalCenters.add(medicalCenter1);
        medicalCenters.add(medicalCenter2);
        medicalCenters.add(medicalCenter3);
        testPosition = new GeographicCoordinates(42.563588, 1.591132);
    }

    @Test
    void calculateTravelTimeBetweenTwoPointsTest() {
        long travelTime = travelTimeCalculator.calculateTravelTimeBetweenTwoPoints(testPosition, medicalCenter3.getGeographicCoordinates());
        // Estimated time by Google Maps = 24 minutes
        assertTrue(1500000 < travelTime);
        assertTrue(travelTime < 2000000);
    }

    @Test
    void findClosestMedicalCenterTest() {
        MedicalCenterWithTravelTime medicalCenterWithTravelTime = travelTimeCalculator.findClosestMedicalCenter(testPosition, medicalCenters);
        assertEquals(medicalCenter1, medicalCenterWithTravelTime.getMedicalCenter());
    }

}