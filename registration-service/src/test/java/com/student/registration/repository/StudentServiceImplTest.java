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
import com.student.registration.service.StudentService;

@SpringBootTest
class StudentServiceImplTest extends BaseTest {
	
	@Autowired
	private StudentService studentService;
	
	@BeforeEach
	void setUp() {
		cleanUpData();
	}
	
	@Test
	void testRegisterStudentWhenValidObjectIsPassed() {
		final Student student =  new Student();
		student.setStudentName("Jhon Doe");
		student.setDateOfBirth(Calendar.getInstance().getTime());
		final Address address =  new Address();
		address.setCountry("United States");
		address.setProvince("Us-east");
		address.setState("Ohaio");
		address.setStreet1("Main Street 1");
		student.setAddress(address);
		Student registertedStudent = studentService.register(student);
		Assertions.assertNotNull(registertedStudent);
	}
	
	@Test
	void testRegisterStudentWhenInvalidObjectIsPassed() {
		final Student student =  new Student();
		student.setStudentName(null);
		student.setDateOfBirth(null);
		final Address address =  new Address();
		address.setCountry("United States");
		address.setProvince("Us-east");
		address.setState("Ohaio");
		address.setStreet1("Main Street 1");
		student.setAddress(address);
		try
		{
			studentService.register(student);
			Assertions.fail();
		} catch(IllegalArgumentException e) {
			Assertions.assertNotNull(e);
		}		
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
		final Student savedStudent  = studentService.register(student);
		Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getStudentId());
		Student retrievedStudent = studentService.getStudentById(savedStudent.getStudentId());
		Assertions.assertNotNull(retrievedStudent);
	}
	
	@Test
	void testRetrieveRegisteredStudentWhenInValidIdisPassed() {
		try {
			studentService.getStudentById(-1L);
			Assertions.fail();
		} catch(StudentNotFoundException e) {
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
		final Student savedStudent  = studentService.register(student);
		savedStudent.setStudentName("Jhon Doe4");
		final Student  mergedStudent =  studentService.merge(savedStudent);
		Assertions.assertNotNull(mergedStudent);
		Student retrievedStudent = studentService.getStudentById(mergedStudent.getStudentId());
		Assertions.assertNotNull(retrievedStudent);
		Assertions.assertEquals(retrievedStudent.getStudentName(), "Jhon Doe4");
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
		final Student savedStudent  = studentService.register(student);
        Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getStudentId());
        studentService.delete(savedStudent.getStudentId());
        try
        {
        	studentService.getStudentById(savedStudent.getStudentId());
        	Assertions.fail();
        }catch(StudentNotFoundException e) {
        	Assertions.assertNotNull(e);
        }
	}

}
