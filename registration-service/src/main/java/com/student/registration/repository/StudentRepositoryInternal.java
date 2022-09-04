package com.student.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.student.registration.model.Student;

@Repository
public interface StudentRepositoryInternal extends CrudRepository<Student, Long> {

}
