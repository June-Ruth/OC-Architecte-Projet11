package com.medhead.selenium;

import com.medhead.selenium.configuration.SeleniumConfiguration;
import com.medhead.selenium.routing.EndToEnd;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeleniumApplication {

	static EndToEnd endToEnd;

	static SeleniumConfiguration configuration;

	SeleniumApplication(SeleniumConfiguration configurationP) {
		configuration = configurationP;
	}

	public static void main(String[] args) {
		endToEnd = new EndToEnd(configuration, "http://localhost:3000");
		endToEnd.login("user", "user");
		endToEnd.findHospital("ALLERGY", "53.135489", "-2.556205");
		endToEnd.goToNewEmergency();
		endToEnd.quit();
	}

}
