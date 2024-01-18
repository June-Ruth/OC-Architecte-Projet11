package com.medhead.selenium.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public interface SeleniumConfiguration {

    WebDriver getDriver();

    Wait<WebDriver> getWebDriverWait();
}
