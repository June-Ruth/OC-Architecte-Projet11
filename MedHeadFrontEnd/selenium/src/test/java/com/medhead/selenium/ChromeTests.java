package com.medhead.selenium;

import com.medhead.selenium.object.Hospital;
import com.medhead.selenium.page.EmergencyPage;
import com.medhead.selenium.page.LoginPage;
import com.medhead.selenium.routing.EndToEnd;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class ChromeTests {

    @Value("${work.directory}")
    private String workDir;
    private String frontAddress;

    private Wait<WebDriver> wait;

    private WebDriver driver;

    private EndToEnd endToEnd;

    private LoginPage loginPage;

    private EmergencyPage emergencyPage;

    private Hospital hospitalExpected;

    @BeforeEach
    void beforeEach() {

        MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:8.0.30")
                .withDatabaseName("medhead")
                .withNetwork(Network.SHARED)
                .withNetworkAliases("mysql")
                .withExposedPorts(3306)
                ;

        sqlContainer.start();

        String workDirDecode = new String(workDir.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        GenericContainer<?> backendContainer = new GenericContainer(
                new ImageFromDockerfile()
                        .withDockerfile(Paths.get(workDirDecode + "/Dockerfile-MedHeadBackEnd")))
                .dependsOn(sqlContainer)
                .withNetwork(sqlContainer.getNetwork())
                .withNetworkAliases("backend")
                .withExposedPorts(8081)
                .withAccessToHost(true)
                .withEnv("DATABASE_DRIVER", sqlContainer.getDriverClassName())
                .withEnv("DATABASE_URL", "jdbc:mysql://mysql:" + MySQLContainer.MYSQL_PORT + "/medhead")
                .withEnv("DATABASE_USERNAME", sqlContainer.getUsername())
                .withEnv("DATABASE_PASSWORD", sqlContainer.getPassword())
                ;

        backendContainer.start();

        GenericContainer<?> frontendContainer =  new GenericContainer<>(
                new ImageFromDockerfile()
                        .withDockerfile(Paths.get(workDirDecode + "/Dockerfile-MedHeadFrontEnd")))
                .dependsOn(backendContainer)
                .withNetwork(backendContainer.getNetwork())
                .withNetworkAliases("frontend")
                .withExposedPorts(3000)
                .withEnv("REACT_APP_PORT", "8081")
                .withEnv("REACT_APP_HOST", "backend")
                ;

        frontendContainer.start();

        BrowserWebDriverContainer<?> chromeContainer = (BrowserWebDriverContainer<?>) new BrowserWebDriverContainer()
                .withCapabilities(new ChromeOptions())
                .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("build"))
                .dependsOn(frontendContainer)
                .withNetwork(frontendContainer.getNetwork())
                .withNetworkAliases("chrome")
                ;

        chromeContainer.start();

        frontAddress = "http://frontend:3000";
        driver = chromeContainer.getWebDriver();
        endToEnd = new EndToEnd(driver, frontAddress);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        emergencyPage = new EmergencyPage(driver);

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
