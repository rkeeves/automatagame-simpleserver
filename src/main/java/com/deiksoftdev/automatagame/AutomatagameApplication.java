package com.deiksoftdev.automatagame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.deiksoftdev.automatagame.controller.UserController;

@SpringBootApplication
public class AutomatagameApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatagameApplication.class, args);
	}

}
