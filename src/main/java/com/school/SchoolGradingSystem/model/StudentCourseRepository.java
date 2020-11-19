package com.school.SchoolGradingSystem.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourse, Long> {
	
	List<StudentCourse> findByName(String Name);

	StudentCourse findById(long id);
	
}
