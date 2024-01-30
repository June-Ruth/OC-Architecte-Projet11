package com.medhead.selenium;

import com.medhead.selenium.configuration.SeleniumConfiguration;
import com.medhead.selenium.object.Hospital;
import com.medhead.selenium.page.EmergencyPage;
import com.medhead.selenium.page.LoginPage;
import com.medhead.selenium.routing.EndToEnd;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles({"chrome", "test"})
public class ChromeTests {

    @Value("${work.directory}")
    private String workDir;

    private String backAddress;

    private String frontAddress;

    @Autowired
    private SeleniumConfiguration configuration;
    private Wait<WebDriver> wait;

    private EndToEnd endToEnd;

    private LoginPage loginPage;
    private EmergencyPage emergencyPage;

    private Hospital hospitalExpected;

    @BeforeEach
    void beforeEach() {
        Network network = Network.newNetwork();

        String dbName = "medhead";
        String dbHost = "mysqlcontainer";
        MySQLContainer<?> SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.30")
                .withDatabaseName(dbName)
                .withNetwork(network)
                .withNetworkAliases(dbHost)
                .withExposedPorts(3306)
                ;

        SQL_CONTAINER.start();

        String jdbcUrl = "jdbc:mysql://" + dbHost + ":" + MySQLContainer.MYSQL_PORT + "/" + dbName;

        String workDirDecode = new String(workDir.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        GenericContainer<?> BACK_CONTAINER = new GenericContainer<>(
                new ImageFromDockerfile()
                        .withDockerfile(Paths.get(workDirDecode + "/Dockerfile-MedHeadBackEnd")))
                .withExposedPorts(8081)
                .withNetwork(network)
                .withAccessToHost(true)
                .withEnv("DATABASE_DRIVER", SQL_CONTAINER.getDriverClassName())
                .withEnv("DATABASE_URL", jdbcUrl)
                .withEnv("DATABASE_USERNAME", SQL_CONTAINER.getUsername())
                .withEnv("DATABASE_PASSWORD", SQL_CONTAINER.getPassword())
                ;

        org.testcontainers.Testcontainers.exposeHostPorts(8081);

        BACK_CONTAINER.start();

        GenericContainer<?> FRONT_CONTAINER = new GenericContainer<>(
                new ImageFromDockerfile()
                        .withDockerfile(Paths.get(workDirDecode + "/Dockerfile-MedHeadFrontEnd")))
                .withNetwork(network)
                .withEnv("REACT_APP_PORT", BACK_CONTAINER.getFirstMappedPort().toString())
                .withEnv("REACT_APP_HOST", BACK_CONTAINER.getHost())
                .withExposedPorts(3000);

        FRONT_CONTAINER.start();

        backAddress = "http://" + BACK_CONTAINER.getHost() + ":" + BACK_CONTAINER.getFirstMappedPort();
        System.out.println(backAddress);
        frontAddress = "http://" + FRONT_CONTAINER.getHost() + ":" + FRONT_CONTAINER.getFirstMappedPort();
        System.out.println(frontAddress);
        endToEnd = new EndToEnd(configuration, frontAddress);
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
