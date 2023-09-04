package com.arthurheliosassignment;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class MainApplication {

	@PostConstruct
	public void init() {
		// Setting Spring Boot SetTimeZone for clean future management
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);

	}

}
