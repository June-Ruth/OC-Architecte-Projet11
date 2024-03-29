package com.medhead.selenium.routing;

import com.medhead.selenium.configuration.SeleniumConfiguration;
import com.medhead.selenium.object.Hospital;
import com.medhead.selenium.page.EmergencyPage;
import com.medhead.selenium.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EndToEnd {

    private SeleniumConfiguration configuration;
    private String url;

    private WebDriver driver;

    private Wait<WebDriver> wait;

    private LoginPage loginPage;

    private EmergencyPage emergencyPage;

    public EndToEnd(SeleniumConfiguration seleniumConfiguration, String urlP) {
        configuration = seleniumConfiguration;
        driver = configuration.getDriver();
        wait = configuration.getWebDriverWait();
        loginPage = new LoginPage(configuration);
        emergencyPage = new EmergencyPage(configuration);
        url = urlP;
        driver.get(url);
    }

    public EndToEnd(WebDriver driverP, String urlP) {
        driver = driverP;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driverP);
        emergencyPage = new EmergencyPage(driverP);
        url = urlP;
        driver.get(url);
    }

    public void login(String username, String password) {
        wait.until(d -> loginPage.getForm().isDisplayed());
        loginPage.getUsernameInput().click();
        loginPage.getUsernameInput().sendKeys(username);
        loginPage.getPasswordInput().click();
        loginPage.getPasswordInput().sendKeys(password);
        loginPage.getSignInButton().click();
    }

    public void findHospital(String speciality, String latitude, String longitude) {
        wait.until(d -> emergencyPage.getForm().isDisplayed());
        emergencyPage.getSpecialityInput().click();
        emergencyPage.getSpecialityInput().sendKeys(speciality);
        emergencyPage.getLatitudeInput().click();
        emergencyPage.getLatitudeInput().sendKeys(latitude);
        emergencyPage.getLongitudeInput().click();
        emergencyPage.getLongitudeInput().sendKeys(longitude);
        emergencyPage.getFindHospitalButton().click();
    }

    public Hospital getHospitalResponse() {
        wait.until(d -> emergencyPage.getHospitalResponse().isDisplayed());
        Hospital hospital = new Hospital();
        hospital.setName(emergencyPage.getHospitalName().getText());
        hospital.setAddress1(emergencyPage.getAddress1().getText());
        hospital.setAddress2(emergencyPage.getAddress2().getText());
        hospital.setAddress3(emergencyPage.getAddress3().getText());
        hospital.setCity(emergencyPage.getCity().getText());
        hospital.setCounty(emergencyPage.getCounty().getText());
        hospital.setPostcode(emergencyPage.getPostcode().getText());
        return hospital;
    }

    public void goToNewEmergency() {
        wait.until(d -> emergencyPage.getHospitalResponse().isDisplayed());
        emergencyPage.getNewEmergencyButton().click();
    }

    public void quit() {
        driver.quit();
    }

}
