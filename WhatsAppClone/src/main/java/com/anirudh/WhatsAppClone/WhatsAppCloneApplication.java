package com.anirudh.WhatsAppClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WhatsAppCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsAppCloneApplication.class, args);
	}

}
