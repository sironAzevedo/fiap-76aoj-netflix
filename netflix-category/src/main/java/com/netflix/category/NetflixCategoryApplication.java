package com.netflix.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCaching
@EnableEurekaClient
@SpringBootApplication
public class NetflixCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixCategoryApplication.class, args);
	}
}
