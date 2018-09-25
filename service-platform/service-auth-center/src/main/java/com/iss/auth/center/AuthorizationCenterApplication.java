package com.iss.auth.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.iss"})
@ComponentScan(value = "com.iss")
public class AuthorizationCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationCenterApplication.class, args);
	}
}
