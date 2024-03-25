package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Database {
//	logger
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	@Bean
	CommandLineRunner initDatabase(ClassRepository classRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Clazz c1 = new Clazz("DHKTPM17C");
				Clazz c2 = new Clazz("DHKTPM17B");
				logger.info("insert data:" +classRepository.save(c1));
				logger.info("insert data:" +classRepository.save(c2));
			}
		};
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
