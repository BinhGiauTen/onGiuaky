package com.example.Test.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Test.models.Product;
import com.example.Test.repositories.ProductRepository;

@Configuration
public class Database {
//	logger
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Product productA = new Product("DienThoai", 2020, 10.3,"");
				Product productB = new Product("May tinh", 2020, 20.0, "");
				logger.info("insert data:" +productRepository.save(productA));
				logger.info("insert data:" +productRepository.save(productB));
			}
		};
	}

}
