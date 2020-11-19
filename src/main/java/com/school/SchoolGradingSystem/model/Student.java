package com.school.SchoolGradingSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(description="All details about the student. ")
public class Student {
	
	@Id
	private Long studentId;
	
	private String name;

	protected Student() {}
	
	public Student(Long studentId, String name) {
	
		this.studentId = studentId;
	    this.name = name;
	  }
	

	public Long getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
