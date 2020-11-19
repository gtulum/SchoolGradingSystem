package com.school.SchoolGradingSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(description="All details about the mark. ")
public class Mark {
	
	@Id
	private Long markId;
	
	private Double mark;
	
	private String markDate;
	
	private String studentName;
	
	private String courseName;
	
	protected Mark() {}
	
	public Mark(Long markId,Double mark, String markDate, String studentName, String courseName) {
		
		this.markId = markId;
	    this.mark = mark;
	    this.markDate = markDate;
	    this.studentName = studentName;
	    this.courseName = courseName;
	  
	  }

	
	
	public Long getMarkId() {
		return markId;
	}

	public Double getMark() {
		return mark;
	}
	
	public String getMarkDate() {
		return markDate;
	}

	public void setMarkId(Long markId) {
		this.markId = markId;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	public void setMarkDate(String markDate) {
		this.markDate = markDate;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
