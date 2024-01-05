package com.casa.samala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SamalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamalaApplication.class, args);
	}

}
