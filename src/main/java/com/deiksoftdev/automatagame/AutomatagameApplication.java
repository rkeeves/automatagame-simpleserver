package com.deiksoftdev.automatagame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AutomatagameApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatagameApplication.class, args);
	}

}
