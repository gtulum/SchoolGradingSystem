package com.school.SchoolGradingSystem.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class StudentCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long Id;
	private Long studentId;
	private String name;
	private Long courseId;
	private String courseName;
	private Long markId;
	private Double mark;
	
	
	@ManyToMany
	@JoinTable(
			name = "taken_courses",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")		
	)
	private List<Course> takenCourses = new ArrayList<>();
	
	
	protected StudentCourse() {}
	
	public StudentCourse(Long studentId, String name, Long courseId, String courseName, Long markId, Double mark) {
	
		//this.Id = id;
		this.studentId = studentId;
	    this.name = name;
	    this.courseId = courseId;
	    this.courseName = courseName;
	    this.markId = markId;
	    this.mark = mark;
	  }
	
	public void takeCourse(Course course) {
		takenCourses.add(course);
	}
	

	public Long getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public Long getCourseId() {
		return courseId;
	}

	public Long getMarkId() {
		return markId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}
    
    
}
