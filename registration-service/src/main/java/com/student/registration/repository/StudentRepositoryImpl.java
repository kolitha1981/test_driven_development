package com.student.registration.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.student.registration.exception.StudentNotFoundException;
import com.student.registration.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	private StudentRepositoryInternal studentRepositoryInternal;

	@Override
	public Student register(Student student) {
		return studentRepositoryInternal.save(student);
	}

	@Override
	public Student getStudentById(final Long studentId) {
		return studentRepositoryInternal.findById(studentId)
		 .orElseThrow(() -> new StudentNotFoundException("Student not found for id :" + studentId));
	}

	@Override
	public Student merge(Student student) {
		final Student studentObj = studentRepositoryInternal.findById(student.getStudentId())
		    .orElseThrow(() -> new StudentNotFoundException("Student not found for id :" + student.getStudentId()));
		studentObj.setAddress(student.getAddress());
		studentObj.setDateOfBirth(student.getDateOfBirth());
		studentObj.setStudentName(student.getStudentName());
		return studentObj;
	}

	@Override
	public void delete(Long studentId) {
		studentRepositoryInternal.deleteById(studentId);
	}

}
