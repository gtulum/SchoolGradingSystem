package com.school.SchoolGradingSystem.model;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
	
	Mark findById(long id);
	
	@Query("select markId FROM Mark mrk where mrk.studentName=:studentName and mrk.courseName=:courseName")
	public List<Long> getMarksForSingleStudentSingleCourse(@Param("studentName") String studentName,
		                                                   @Param("courseName") String courseName);
			
	@Query("select markId FROM Mark mrk where mrk.studentName=:studentName order by courseName")
	public List<Long> getMarksForSingleStudentAllCourse(@Param("studentName") String studentName);

}
