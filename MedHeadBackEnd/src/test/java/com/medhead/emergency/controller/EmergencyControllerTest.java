package com.medhead.emergency.controller;

import com.medhead.emergency.entity.GeographicCoordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
public class EmergencyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Container
    private static final MySQLContainer<?> SQL_CONTAINER = new MySQLContainer<>().withDatabaseName("medhead");

    @DynamicPropertySource
    static void configureMysqlProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", SQL_CONTAINER::getPassword);
    }

    private GeographicCoordinates testPosition;

    @BeforeEach
    void beforeEach() {
        testPosition = new GeographicCoordinates(42.563588, 1.591132);
    }


    @Test
    @WithMockUser(roles = "USER")
    public void getMedicalCenterBySpecialityAndLocalisationAuthenticatedUserTest() throws Exception {
        mvc.perform(get("/emergency/hospital")
                        .queryParam("speciality", "ALLERGY")
                        .queryParam("latitude", String.valueOf(testPosition.getLatitude()))
                        .queryParam("longitude", String.valueOf(testPosition.getLongitude())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialities").isArray());
    }

}
