package com.project.web_service.service;

import java.util.List;

import com.project.web_service.model.Student;

public interface StudentService {
	Student saveStudent(Student student);
	List<Student> getAllStudent();
	Student getStudentById(long id);
	Student updateStudent(Student student, long id);
	Student getStudentByEmail(String email);
	void deleteStudent(long id);

}
