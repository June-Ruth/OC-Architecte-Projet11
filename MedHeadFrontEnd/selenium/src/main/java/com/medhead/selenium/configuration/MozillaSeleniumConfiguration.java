package com.medhead.selenium.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;

@Profile("mozilla")
@Configuration
public class MozillaSeleniumConfiguration implements SeleniumConfiguration {

    private WebDriver driver;

    private Wait<WebDriver> wait;

    public MozillaSeleniumConfiguration() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public Wait<WebDriver> getWebDriverWait() {
        return wait;
    }

}
