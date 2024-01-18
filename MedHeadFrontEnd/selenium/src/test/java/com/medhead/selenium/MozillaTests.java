package com.medhead.selenium;

import com.medhead.selenium.configuration.SeleniumConfiguration;
import com.medhead.selenium.object.Hospital;
import com.medhead.selenium.page.EmergencyPage;
import com.medhead.selenium.page.LoginPage;
import com.medhead.selenium.routing.EndToEnd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("mozilla")
public class MozillaTests {

    @Autowired
    private SeleniumConfiguration configuration;
    private Wait<WebDriver> wait;

    private EndToEnd endToEnd;

    private LoginPage loginPage;
    private EmergencyPage emergencyPage;

    private Hospital hospitalExpected;

    @BeforeEach
    void beforeEach() {
        endToEnd = new EndToEnd(configuration);
        wait = configuration.getWebDriverWait();
        loginPage = new LoginPage(configuration);
        emergencyPage = new EmergencyPage(configuration);
        hospitalExpected = new Hospital();
        hospitalExpected.setName("Walton Community Hospital - Virgin Care Services Ltd");
        hospitalExpected.setAddress2("Rodney Road");
        hospitalExpected.setCity("Walton-on-Thames");
        hospitalExpected.setCounty("Surrey");
        hospitalExpected.setPostcode("KT12 3LD");
    }


    @Test
    void endToEndTest() {
        wait.until(d -> loginPage.getForm().isDisplayed());
        assertTrue(loginPage.getForm().isDisplayed());
        endToEnd.login("user", "user");
        wait.until(d -> emergencyPage.getForm().isDisplayed());
        assertTrue(emergencyPage.getForm().isDisplayed());
        endToEnd.findHospital("ALLERGY", "42.563588", "1.591132");
        wait.until(d -> emergencyPage.getHospitalResponse().isDisplayed());
        assertTrue(emergencyPage.getHospitalResponse().isDisplayed());
        Hospital hospital = endToEnd.getHospitalResponse();
        assertEquals(hospitalExpected.getName(), hospital.getName());
        assertEquals(hospitalExpected.getAddress2(), hospital.getAddress2());
        assertEquals(hospitalExpected.getCity(), hospital.getCity());
        assertEquals(hospitalExpected.getCounty(), hospital.getCounty());
        assertEquals(hospitalExpected.getPostcode(), hospital.getPostcode());
        endToEnd.goToNewEmergency();
        wait.until(d -> emergencyPage.getForm().isDisplayed());
        assertTrue(emergencyPage.getForm().isDisplayed());
        endToEnd.quit();
    }
}
