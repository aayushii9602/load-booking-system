package com.loadbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Load Booking API", version = "1.0", description = "Documentation for Load & Booking APIs"))

@SpringBootApplication
public class LoadBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadBookingSystemApplication.class, args);
	}

}
