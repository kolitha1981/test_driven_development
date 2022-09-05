package com.student.registration.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "REGISTERED_STUDENTS", schema = "STUDENT_REGISTRATION_SCHEMA")
@Getter
@Setter
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private Long studentId;
	
	@Column(name = "STUDENT_NAME")	
	private String studentName;
	
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Embedded
	private Address address;

	public boolean isValid() {
		return Objects.nonNull(address) && Objects.nonNull(dateOfBirth) 
				&& Objects.nonNull(studentName);
	}

}
