package com.example.uberReview2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UberReview2Application {

	public static void main(String[] args) {
		SpringApplication.run(UberReview2Application.class, args);
	}

}
