package com.netflix.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
public class NetflixGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixGatewayApplication.class, args);
	}
}
