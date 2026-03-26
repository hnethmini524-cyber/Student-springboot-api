package com.project.web_service.controller;

import java.util.List;

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

import com.project.web_service.model.Student;
import com.project.web_service.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@PostMapping()
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
	}
	
	//build create get all students
	@GetMapping
	public List<Student> getAllStudent(){
		return studentService.getAllStudent();
		
	}
	
	//build create get all students by id
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id")long studentId){
		return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);
	}
	
	//build update student
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id")long id, @RequestBody Student student){
		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
	}
	
	//build delete student
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id")long id){
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("Student deleted successfully!", HttpStatus.OK);
	}
	
	//find student by email
	@GetMapping("/email/{email}")
	public ResponseEntity<Student> getStudentByEmail(@PathVariable String email){
	    return new ResponseEntity<Student>(studentService.getStudentByEmail(email),HttpStatus.OK);
	}
	

}

