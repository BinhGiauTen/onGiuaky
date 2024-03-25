package com.example.Test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Test.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
