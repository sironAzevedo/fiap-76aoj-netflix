package com.netflix.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NetflixUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixUserApplication.class, args);
	}
}
