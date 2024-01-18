package com.medhead.selenium.page;

import com.medhead.selenium.configuration.SeleniumConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmergencyPage {

    private final WebDriver driver;

    public EmergencyPage(SeleniumConfiguration configurationP) {
        driver = configurationP.getDriver();
    }

    private WebElement form;

    private WebElement specialityInput;

    private WebElement latitudeInput;

    private WebElement longitudeInput;

    private WebElement findHospitalButton;

    private WebElement hospitalResponse;

    private WebElement newEmergencyButton;

    private WebElement hospitalName;

    private WebElement address1;

    private WebElement address2;

    private WebElement address3;

    private WebElement city;

    private WebElement county;

    private WebElement postcode;

    public WebElement getForm() {
        return driver.findElement(By.id("emergencyForm"));
    }

    public WebElement getSpecialityInput() {
        return driver.findElement(By.id("speciality"));
    }

    public WebElement getLatitudeInput() {
        return driver.findElement(By.id("latitude"));
    }

    public WebElement getLongitudeInput() {
        return driver.findElement(By.id("longitude"));
    }

    public WebElement getFindHospitalButton() {
        return driver.findElement(By.id("findHospital"));
    }

    public WebElement getHospitalResponse() {
        return driver.findElement(By.id("hospitalResponse"));
    }

    public WebElement getNewEmergencyButton() {
        return driver.findElement(By.id("newEmergency"));
    }

    public WebElement getHospitalName() {
        return driver.findElement(By.id("hospitalName"));
    }

    public WebElement getAddress1() {
        return driver.findElement(By.id("address1"));
    }

    public WebElement getAddress2() {
        return driver.findElement(By.id("address2"));
    }

    public WebElement getAddress3() {
        return driver.findElement(By.id("address3"));
    }

    public WebElement getCity() {
        return driver.findElement(By.id("city"));
    }

    public WebElement getCounty() {
        return driver.findElement(By.id("county"));
    }

    public WebElement getPostcode() {
        return driver.findElement(By.id("postcode"));
    }
}
