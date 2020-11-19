package com.school.SchoolGradingSystem.model;

public class Report{
	
		private String studentName;
		private String courseName;
		private Double average;

		public Report() {}
		
		public Report(String studentName, String courseName, Double average) {
		
		    this.studentName = studentName;
		    this.courseName = courseName;
		    this.average = average;
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

		public Double getAverage() {
			return average;
		}

		public void setAverage(Double average) {
			this.average = average;
		}

}
