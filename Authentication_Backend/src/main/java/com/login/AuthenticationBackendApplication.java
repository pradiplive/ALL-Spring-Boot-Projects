package com.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.login.service.AuthenticateOTPService;

@SpringBootApplication
@EnableFeignClients
public class AuthenticationBackendApplication {
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationBackendApplication.class, args);
	}

}
