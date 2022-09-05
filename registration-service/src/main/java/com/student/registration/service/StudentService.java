package com.student.registration.service;

import com.student.registration.model.Student;

public interface StudentService {
	
    Student register(final Student student);
	
	Student getStudentById(final Long studentId);
	
	Student merge(final Student student);
	
	void delete(final Long studentId);

}
