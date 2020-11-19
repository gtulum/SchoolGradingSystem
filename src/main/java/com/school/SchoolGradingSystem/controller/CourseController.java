package com.school.SchoolGradingSystem.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.SchoolGradingSystem.model.Course;
import com.school.SchoolGradingSystem.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
@Api(tags = "Courses")
public class CourseController {

	
	@Autowired
	private CourseService courseService;
	
	
	@ApiResponses(
            value={
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )

	
	@ApiOperation(value ="List of Courses (For teachers only)")
	@GetMapping("/getAllCourses")
	@PreAuthorize("hasRole('TEACHER')")
	public List<Course> allCourses() {

	    return courseService.getAllCourses();
	}

	@ApiOperation(value ="Find course by id (For teachers only)")
	@GetMapping("/getCourseById/{id}")
	@PreAuthorize("hasRole('TEACHER')")
	public Course getCourseById(@PathVariable("id") long id) {
		
       return courseService.getCourseById(id);
	}
	
	@ApiOperation(value ="Find course by name (For teachers only)")
	@GetMapping("/getCourseByName/{name}")
	@PreAuthorize("hasRole('TEACHER')")
	public List<Course> getCourseByName(@PathVariable("name") String name) {
		
	   return courseService.getCourseByName(name);
			
	}
	
	@ApiOperation(value ="Create a new course (For teachers only)")
	@PostMapping("/addCourse")
	@PreAuthorize("hasRole('TEACHER')")
	public void addCourse(@RequestBody Course course){
		  
		courseService.addCourse(course);
	}
		
	@ApiOperation(value ="Update course (For teachers only)")
	@PutMapping("/updateCourse/{id}")
	@PreAuthorize("hasRole('TEACHER')")
	public void updateCourse(@RequestBody Course course,@PathVariable("id") long id){
		  
		courseService.updateCourse(id, course);
	}
	
	@ApiOperation(value ="Delete course (For teachers only)")
	@DeleteMapping("/deleteCourse/{id}")
	@PreAuthorize("hasRole('TEACHER')")
    public void deleteCourse(@PathVariable("id") long id){
		  
		courseService.deleteCourse(id);
	}
	  
	
}
