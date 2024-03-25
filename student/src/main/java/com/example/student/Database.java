package com.example.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
//	logger
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	@Bean
	CommandLineRunner initDatabase(StudentRepository studentRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Student s1 = new Student("Tran Van Binh", "binhpro50@gmail.com", "1");
				Student s2 = new Student("Nguyen Van An", "an@gmail.com", "2");
				logger.info("insert data:" +studentRepository.save(s1));
				logger.info("insert data:" +studentRepository.save(s2));
			}
		};
	}
}
