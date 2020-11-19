package com.school.SchoolGradingSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.SchoolGradingSystem.model.Course;
import com.school.SchoolGradingSystem.model.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getAllCourses()
    {
        List<Course> courseList = (List<Course>) courseRepository.findAll();
         
        if(courseList.size() > 0) {
            return courseList;
        } else {
            return new ArrayList<Course>();
        }
    }
	
	public Course getCourseById(long id) {
		return courseRepository.findById(id);
	}
	
	public List<Course> getCourseByName(String name) {
		return courseRepository.findByName(name);
	}
	
	public void addCourse(Course course) {
		courseRepository.save(course);
	}
	
	public void updateCourse(long id,Course course) {
		
		Course cr = courseRepository.findById(id);	
		cr.setName(course.getName());
		courseRepository.save(cr);		         
	}
	
	public void deleteCourse(long id) {
		courseRepository.deleteById(id);
	}

}
