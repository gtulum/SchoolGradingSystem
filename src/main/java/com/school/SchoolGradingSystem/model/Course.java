package com.school.SchoolGradingSystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(description="All details about the course. ")
public class Course {
	
	@Id
	private Long Id;
	
	private String name;
	
	@ManyToMany( mappedBy = "takenCourses", cascade = CascadeType.ALL)
	private List<StudentCourse> studentList = new ArrayList<>();
	
	protected Course() {}
	
	public Course(Long Id,String name) {
		this.Id = Id;
	    this.name = name;   
	  }
	
	
	public void addStudent(StudentCourse studentCourse) {
		studentList.add(studentCourse);
	}


	public Long getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		Id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
