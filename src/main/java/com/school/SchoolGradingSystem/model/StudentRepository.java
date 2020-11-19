package com.school.SchoolGradingSystem.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
	List<Student> findByName(String Name);

	Student findById(long id);

}
