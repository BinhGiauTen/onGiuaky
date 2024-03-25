package com.example.student;


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
@RequestMapping(path = "api/v1/students")
public class StudentController {
	
//	DI = Dependency injection
	@Autowired
	private StudentRepository repository;
	
	@Autowired
    private ClassServiceClient classServiceClient;
	
	@GetMapping("")
	List<Student> getAllStudents(){
		return repository.findAll();
	}

	 @GetMapping("/{id}")
	    ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	        Student student = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
	        String className = classServiceClient.getClassName(student.getId());
	        student.setClassId(className); // Thêm tên lớp vào thông tin sinh viên
	        return ResponseEntity.ok(student);
	 }
	 @GetMapping("/class/{classId}")
	    public ResponseEntity<List<Student>> getStudentsByClassId(@PathVariable String classId) {
	        List<Student> studentsInClass = repository.findByClassId(classId);
	        return ResponseEntity.ok(studentsInClass);
	 }
	 
//	 Get detail product
//	 @GetMapping("/{id}")
//	 ResponseEntity<Student> getStudentById(@PathVariable Long id) {
//	     Student student = repository.findById(id)
//	         .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
//	     return ResponseEntity.ok(student);
//	 }
//	 Let's return an object with: data, message, status
	 ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
	 	Optional<Student> foundStudent = repository.findById(id);
	 	return foundStudent.isPresent()?
	 			ResponseEntity.status(HttpStatus.OK).body(
	 				new ResponseObject("ok", "Query student successfully", foundStudent)
	 			):
	 			ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	 				new ResponseObject("failed", "Cannot find student with id = "+id, ""));
	 }
	

	
}
