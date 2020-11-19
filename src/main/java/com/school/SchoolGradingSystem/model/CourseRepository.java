package com.school.SchoolGradingSystem.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
	
	List<Course> findByName(String Name);

	Course findById(long id);

}