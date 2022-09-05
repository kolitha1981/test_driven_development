package com.student.registration.repository;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest {
	
	@Autowired
	private StudentRepositoryInternal studentRepositoryInternal;
	
	public void cleanUpData() {
		studentRepositoryInternal.deleteAll();
	}

}
