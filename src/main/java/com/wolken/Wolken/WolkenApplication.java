package com.wolken.Wolken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wolken.Wolken")
public class WolkenApplication {

	public static void main(String[] args) {
		SpringApplication.run(WolkenApplication.class, args);
	}

}
