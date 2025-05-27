package com.invextory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class InvextoryApiSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(InvextoryApiSpringApplication.class, args);
	}
}
