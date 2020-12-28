package com.netflix.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NetflixAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixAuthorizationApplication.class, args);
	}

}
