package com.project.web_service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.web_service.exception.ResourceNotFound;
import com.project.web_service.model.Student;
import com.project.web_service.repository.StudentRepository;
import com.project.web_service.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	@Override
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}
	
	@Override
	public Student getStudentById(long id) {
		Optional<Student> student = studentRepository.findById(id);
		
	if(student.isPresent()) {
		return student.get();
	}else {
		throw new ResourceNotFound("Student","Id",id);
	}
	}
	
	@Override
	public Student updateStudent(Student student, long id) {
		Student existingStudent = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFound("Student","Id",id));
		
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		// save existing student to db
		studentRepository.save(existingStudent);
		return existingStudent;
	}
	
	@Override
	public void deleteStudent(long id) {
		
		studentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Student","Id",id));
		studentRepository.deleteById(id);
	}
	
	@Override
	public Student getStudentByEmail(String email) {
	    return studentRepository.findByEmail(email)
	            .orElseThrow(() -> new ResourceNotFound("Student","Email",email));
	}


}

