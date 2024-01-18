package com.medhead.selenium.routing;

import com.medhead.selenium.configuration.SeleniumConfiguration;
import com.medhead.selenium.object.Hospital;
import com.medhead.selenium.page.EmergencyPage;
import com.medhead.selenium.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class EndToEnd {

    private SeleniumConfiguration configuration;
    private static final String URL = "http://localhost:3000";

    private WebDriver driver;

    private Wait<WebDriver> wait;

    private LoginPage loginPage;

    private EmergencyPage emergencyPage;

    public EndToEnd(SeleniumConfiguration seleniumConfiguration) {
        configuration = seleniumConfiguration;
        driver = configuration.getDriver();
        wait = configuration.getWebDriverWait();
        loginPage = new LoginPage(configuration);
        emergencyPage = new EmergencyPage(configuration);
        driver.get(URL);
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
