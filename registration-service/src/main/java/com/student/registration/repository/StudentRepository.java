package com.student.registration.repository;

import com.student.registration.model.Student;

public interface StudentRepository {
	
	Student register(final Student student);
	
	Student getStudentById(final Long studentId);
	
	Student merge(final Student student);
	
	void delete(final Long studentId);

}
