package com.pharma.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PharmaRmsInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmaRmsInventoryApplication.class, args);
	}

}
