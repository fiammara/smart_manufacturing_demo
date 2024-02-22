package com.manufacture.expertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExpertServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpertServiceApplication.class, args);
	}

}
