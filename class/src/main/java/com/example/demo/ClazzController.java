package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/classes")
public class ClazzController {
	
//	DI = Dependency injection
	@Autowired
	private ClassRepository repository;
	
	@GetMapping("")
	List<Clazz> getAllClazzs(){
		return repository.findAll();
	}
	
//	Get detail product
	@GetMapping("/{id}")
	ResponseEntity<Clazz> getClassById(@PathVariable Long id) {
        Clazz clazz = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Class not found with id " + id));
        return ResponseEntity.ok(clazz);
    }
//	Let's return an object with: data, message, status
//	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
//		Optional<Clazz> foundClass = repository.findById(id);
//		return foundClass.isPresent()?
//				ResponseEntity.status(HttpStatus.OK).body(
//					new ResponseObject("ok", "Query student successfully", foundClass)
//				):
//				ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//					new ResponseObject("failed", "Cannot find student with id = "+id, ""));
//	}
	
	
	

	
}
