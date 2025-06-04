package com.ac.dha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.ac.dha.entities")
public class DhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DhaApplication.class, args);
	}

}
