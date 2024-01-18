package com.medhead.selenium.page;

import com.medhead.selenium.configuration.SeleniumConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(SeleniumConfiguration configurationP) {
        driver = configurationP.getDriver();
    }

    private WebElement form;

    private WebElement usernameInput;

    private WebElement passwordInput;

    private WebElement signInButton;


    public WebElement getForm() {
        return driver.findElement(By.id("loginForm"));
    }

    public WebElement getUsernameInput() {
        return driver.findElement(By.id("username"));
    }

    public WebElement getPasswordInput() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getSignInButton() {
        return driver.findElement(By.id("signIn"));
    }

}
