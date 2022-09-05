package com.student.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.registration.model.Student;
import com.student.registration.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepositiry;

	@Override
	@Transactional
	public Student register(Student student) {
		if(!student.isValid()) {
			throw new IllegalArgumentException("The student to be registeretd is not valid");
		}
		return studentRepositiry.register(student);
	}

	@Override
	@Transactional(readOnly = true)
	public Student getStudentById(Long studentId) {
		return studentRepositiry.getStudentById(studentId);
	}

	@Override
	@Transactional
	public Student merge(Student student) {
		return studentRepositiry.merge(student);
	}

	@Override
	public void delete(Long studentId) {
		studentRepositiry.delete(studentId);
	}

}
