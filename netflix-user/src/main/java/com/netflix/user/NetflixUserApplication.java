package com.netflix.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NetflixUserApplication implements CommandLineRunner {
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(NetflixUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("BCRYPT = " + passwordEncoder.encode("123456"));		
	}
}
