package com.sricare.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableDiscoveryClient
public class MicroServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesApplication.class, args);
	}

}
