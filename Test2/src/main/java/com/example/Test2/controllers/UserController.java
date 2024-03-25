package com.example.Test2.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Test2.models.ResponseObject;
import com.example.Test2.models.Usser;
import com.example.Test2.repositories.UserRepository;


@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
	
//	DI = Dependency injection
	@Autowired
	private UserRepository repository;
	
	@GetMapping("")
	List<Usser> getAllUsers(){
		return repository.findAll();
	}
	@GetMapping("/demo")
	public ResponseEntity<String> demo(){
		return ResponseEntity.ok("Hello from secured url");
	}
	
//	Get detail product
	@GetMapping("/{id}")
//	Let's return an object with: data, message, status
	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Usser> foundUser = repository.findById(id);
		return foundUser.isPresent()?
				ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", "Query user successfully", foundUser)
				):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("failed", "Cannot find user with id = "+id, ""));
	}
	
//	insert new Product with POST method
//	Postman :Raw, JSON
	@PostMapping("/insert")
	ResponseEntity<ResponseObject> insertUser(@RequestBody Usser newUser){
		return ResponseEntity.status(HttpStatus.OK).body(
			new ResponseObject("ok", "Insert user successfully", repository.save(newUser))
		);
				
	}
	
//	update, upsert = update if found, otherwise insert
	@PutMapping("/{id}")
	ResponseEntity<ResponseObject> updateUser(@RequestBody Usser newUser, @PathVariable Long id){
		Usser updatedUser = repository.findById(id)
			.map(user ->{
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setUserName(newUser.getUserName());
				user.setPassWord(newUser.getPassWord());
				user.setRole(newUser.getRole());
			return repository.save(user);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
		return ResponseEntity.status(HttpStatus.OK).body(
			new ResponseObject("ok", "Update user successfully", updatedUser)
		);
	}
	
//	Delete a Product 
	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id){
		boolean exists = repository.existsById(id);
		if(exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "Delete user successfully", "")
			);
		}return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
			new ResponseObject("failed", "Cannot find user to delete", "")
		);
	}
	
}
