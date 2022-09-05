package com.student.registration.repository;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.student.registration.exception.StudentNotFoundException;
import com.student.registration.model.Address;
import com.student.registration.model.Student;

@SpringBootTest
class StudentRepositoryImplTest extends BaseTest {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@BeforeEach
	void setUp() {
		cleanUpData();
	}
	
	@Test
	void testRegsiterStudentWhenValidStudentObjectIsPassed() {
		final Student student =  new Student();
		student.setStudentName("Jhon Doe");
		student.setDateOfBirth(Calendar.getInstance().getTime());
		final Address address =  new Address();
		address.setCountry("United States");
		address.setProvince("Us-east");
		address.setState("Ohaio");
		address.setStreet1("Main Street 1");
		student.setAddress(address);
		final Student savedStudent  = studentRepository.register(student);
        Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getStudentId());
	}
	
	@Test
	void testRetrieveRegisteredStudentWhenValidIdisPassed() {
		final Student student =  new Student();
		student.setStudentName("Jhon Doe1");
		student.setDateOfBirth(Calendar.getInstance().getTime());
		final Address address =  new Address();
		address.setCountry("United States");
		address.setProvince("Us-east");
		address.setState("Ohaio");
		address.setStreet1("Main Street 1");
		student.setAddress(address);
		final Student savedStudent  = studentRepository.register(student);
		Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getStudentId());
		Student retrievedStudent = studentRepository.getStudentById(savedStudent.getStudentId());
		Assertions.assertNotNull(retrievedStudent);
		Assertions.assertEquals(retrievedStudent.getStudentId(), savedStudent.getStudentId());
	}
	
	@Test
	void testRetrieveRegisteredStudentWhenInValidIdisPassed() {
		try {
			studentRepository.getStudentById(-1L);
			Assertions.fail();
		} catch( StudentNotFoundException e) {
			Assertions.assertNotNull(e);
		}
	}
	
	@Test
	void testMergeStudentWhenValidStudentIsPassed() {
		final Student student =  new Student();
		student.setStudentName("Jhon Doe3");
		student.setDateOfBirth(Calendar.getInstance().getTime());
		final Address address =  new Address();
		address.setCountry("United States");
		address.setProvince("Us-east");
		address.setState("Ohaio");
		address.setStreet1("Main Street 1");
		student.setAddress(address);
		final Student savedStudent  = studentRepository.register(student);
		savedStudent.setStudentName("Jhon Doe4");
		final Student  mergedStudent =  studentRepository.merge(savedStudent);
		Assertions.assertNotNull(mergedStudent);
		Assertions.assertEquals(mergedStudent.getStudentName(), "Jhon Doe4");
	}
	
	@Test
	void testMergeStudentWhenInValidStudentIsPassed() {
		final Student student =  new Student();
		student.setStudentName("Jhon Doe3");
		student.setDateOfBirth(Calendar.getInstance().getTime());
		final Address address =  new Address();
		address.setCountry("United States");
		address.setProvince("Us-east");
		address.setState("Ohaio");
		address.setStreet1("Main Street 1");
		student.setAddress(address);
		final Student savedStudent  = studentRepository.register(student);
		savedStudent.setStudentId(-1L);
		savedStudent.setStudentName("Jhon Doe4");
		try
		{
			studentRepository.merge(savedStudent);
			Assertions.fail();
		}catch(StudentNotFoundException e) {
			Assertions.assertNotNull(e);
		}
	}
	
	@Test
	void testDeleteStudentWhenValidStudentIdIsPassed() {
		final Student student =  new Student();
		student.setStudentName("Jhon Doe");
		student.setDateOfBirth(Calendar.getInstance().getTime());
		final Address address =  new Address();
		address.setCountry("United States");
		address.setProvince("Us-east");
		address.setState("Ohaio");
		address.setStreet1("Main Street 1");
		student.setAddress(address);
		final Student savedStudent  = studentRepository.register(student);
        Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getStudentId());
        studentRepository.delete(savedStudent.getStudentId());
        try
        {
        	studentRepository.getStudentById(savedStudent.getStudentId());
        	Assertions.fail();
        }catch(StudentNotFoundException e) {
        	Assertions.assertNotNull(e);
        }
		
	}

}
