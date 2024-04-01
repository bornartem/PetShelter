package com.example.petShelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class PetShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShelterApplication.class, args);

	}

}
