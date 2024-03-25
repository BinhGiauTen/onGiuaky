package com.example.Test.controllers;

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

import com.example.Test.models.Product;
import com.example.Test.models.ResponseObject;
import com.example.Test.repositories.ProductRepository;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
	
//	DI = Dependency injection
	@Autowired
	private ProductRepository repository;
	
	@GetMapping("")
	List<Product> getAllProducts(){
		return repository.findAll();
	}
	
//	Get detail product
	@GetMapping("/{id}")
//	Let's return an object with: data, message, status
	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Product> foundProduct = repository.findById(id);
		return foundProduct.isPresent()?
				ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", "Query product successfully", foundProduct)
				):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("failed", "Cannot find product with id = "+id, ""));
	}
	
//	insert new Product with POST method
//	Postman :Raw, JSON
	@PostMapping("/insert")
	ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
		return ResponseEntity.status(HttpStatus.OK).body(
			new ResponseObject("ok", "Insert product successfully", repository.save(newProduct))
		);
				
	}
	
//	update, upsert = update if found, otherwise insert
	@PutMapping("/{id}")
	ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
		Product updatedProduct = repository.findById(id)
			.map(product ->{
				product.setProductName(newProduct.getProductName());
				product.setYear(newProduct.getYear());
				product.setPrice(newProduct.getPrice());
				product.setUrl(newProduct.getUrl());
			return repository.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return repository.save(newProduct);
		});
		return ResponseEntity.status(HttpStatus.OK).body(
			new ResponseObject("ok", "Update product successfully", updatedProduct)
		);
	}
	
//	Delete a Product 
	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
		boolean exists = repository.existsById(id);
		if(exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "Delete product successfully", "")
			);
		}return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
			new ResponseObject("failed", "Cannot find product to delete", "")
		);
	}
	
}
