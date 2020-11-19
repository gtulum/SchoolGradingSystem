package com.school.SchoolGradingSystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<StudentCourse, Long> {


	@Query("select AVG(mark) FROM StudentCourse sc where sc.studentId=:studentId and sc.courseId=:courseId")
	public Double getAverageMarkForSingleStudentSingleCourse(@Param("studentId") Long studentId,
                                                             @Param("courseId")  Long courseId);
	
	
	@Query("select AVG(mark) FROM StudentCourse sc where sc.studentId=:studentId group by sc.courseId")
	public List<Double> getAverageMarkForSingleStudentAllCourse(@Param("studentId") Long studentId);
	
	
	@Query("select AVG(mark) FROM StudentCourse sc where sc.courseId=:courseId group by sc.studentId")
	public List<Double> getAverageMarkForAllStudentsSingleCourse(@Param("courseId") Long courseId);
	
	       
	@Query("select AVG(mark) FROM StudentCourse sc group by sc.courseId,sc.studentId")
	public List<Double> getAverageMarkForAllStudentsAllCourses();



}


