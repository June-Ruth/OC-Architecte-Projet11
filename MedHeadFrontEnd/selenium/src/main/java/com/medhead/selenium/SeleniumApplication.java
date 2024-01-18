package com.medhead.selenium;

import com.medhead.selenium.configuration.ChromeSeleniumConfiguration;
import com.medhead.selenium.configuration.SeleniumConfiguration;
import com.medhead.selenium.routing.EndToEnd;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SeleniumApplication {

	static EndToEnd endToEnd;

	static SeleniumConfiguration configuration;

	SeleniumApplication(SeleniumConfiguration configurationP) {
		configuration = configurationP;
	}

	public static void main(String[] args) {
		endToEnd = new EndToEnd(configuration);
		endToEnd.login("user", "user");
		endToEnd.findHospital("ALLERGY", "53.135489", "-2.556205");
		endToEnd.goToNewEmergency();
		endToEnd.quit();
	}

}
