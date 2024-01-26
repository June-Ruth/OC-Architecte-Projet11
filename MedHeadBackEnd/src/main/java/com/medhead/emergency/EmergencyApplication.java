package com.medhead.emergency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class EmergencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmergencyApplication.class, args);
	}

}
