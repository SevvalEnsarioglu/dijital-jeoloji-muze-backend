package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DijitalJeolojiMuzeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DijitalJeolojiMuzeBackendApplication.class, args);
	}

}
