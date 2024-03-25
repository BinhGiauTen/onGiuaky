package com.example.Test2.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Test2.models.Usser;


public interface UserRepository extends JpaRepository<Usser, Long>{
	Optional<Usser> findByUserName(String userName);
	
}