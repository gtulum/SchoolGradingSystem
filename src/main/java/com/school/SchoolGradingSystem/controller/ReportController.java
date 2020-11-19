package com.school.SchoolGradingSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.SchoolGradingSystem.model.ERole;
import com.school.SchoolGradingSystem.model.Report;
import com.school.SchoolGradingSystem.model.ReportRepository;
import com.school.SchoolGradingSystem.service.CourseService;
import com.school.SchoolGradingSystem.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reports")
@Api(tags = "Average Reports")
public class ReportController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@ApiResponses(
            value={
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
	
	@ApiOperation(value ="Return average mark for a single student has in a single course (For teachers and students)")  
	@GetMapping("/getAvgMarkForSingleStudentSingleCourse")
	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@ResponseBody
	public Report getAverageMarkForSingleStudentSingleCourse(@RequestParam(name = "studenId") Long studentId, @RequestParam(name="courseId") Long courseId) {
		
		Report report = new Report();
		String studentName = studentService.getStudentById(studentId).getName();		
	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          
	        List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
	        
	        if(roles.get(0).equals(ERole.ROLE_STUDENT.name())) {
	        	
	        	 if(userDetails.getUsername().equals(studentName)) {
	        		 
	        			report = new Report(studentName,courseService.getCourseById(courseId).getName(),reportRepository.getAverageMarkForSingleStudentSingleCourse(studentId, courseId));
	        	 }
	        	 
	        	 
	        }else {
	        	
	    		report = new Report(studentName,courseService.getCourseById(courseId).getName(),reportRepository.getAverageMarkForSingleStudentSingleCourse(studentId, courseId));
	        	
	        }	
		
		return report;
	}
	
	
	@ApiOperation(value ="Return average mark for a single student has across all courses (For teachers and students)")
	@GetMapping("/getAvgMarkForSingleStudentAllCourse")
	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@ResponseBody
	public List<Report> getAverageMarkForSingleStudentAllCourse(@RequestParam(name = "studenId") Long studentId) {
		
		List<Report> reportList = new ArrayList<Report>();		 	
		String studentName = studentService.getStudentById(studentId).getName();		
	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          
	        List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
	        
	        if(roles.get(0).equals(ERole.ROLE_STUDENT.name())) {
	        	
	        	 if(userDetails.getUsername().equals(studentName)) {
	        		 
	        		 for(int i=0; i<courseService.getAllCourses().size();i++) {
	        				Report report = new Report(studentName,courseService.getAllCourses().get(i).getName(),reportRepository.getAverageMarkForSingleStudentAllCourse(studentId).get(i));
	        				reportList.add(report);
	        			}	        	
	        	 }   
	        	 
	        }else {
	        	
	        	for(int i=0; i<courseService.getAllCourses().size();i++) {
	    			Report report = new Report(studentName,courseService.getAllCourses().get(i).getName(),reportRepository.getAverageMarkForSingleStudentAllCourse(studentId).get(i));
	    			reportList.add(report);
	    		}	        	
	        }	
	
       return reportList;
	}
	  
	

	  @ApiOperation(value ="Return average mark for all students were given in a single course (For teachers only)")
	  @GetMapping("/getAvgMarkForAllStudentsSingleCourse")
	  @PreAuthorize("hasRole('TEACHER')")
	  @ResponseBody
	  public List<Report> getAverageMarkForAllStudentsSingleCourse(@RequestParam(name = "courseId") Long courseId) {
		  List<Report> reportList = new ArrayList<Report>();
		  int i;
		  for(i=0; i<studentService.getAllStudents().size();i++) {
				 Report report = new Report(studentService.getAllStudents().get(i).getName(),courseService.getCourseById(courseId).getName(),reportRepository.getAverageMarkForAllStudentsSingleCourse(courseId).get(i));
				 reportList.add(report);
		  }
		 
		return reportList;
	  }
	  
	  
	  @ApiOperation(value ="Return average mark for all students were given in all courses (For teachers only)")
	  @GetMapping("/getAvgMarkForAllStudentsAllCourses")
	  @PreAuthorize("hasRole('TEACHER')")
	  @ResponseBody
	  public List<Report> getAverageMarkForAllStudentsAllCourses() {
		  List<Report> reportList = new ArrayList<Report>();
		  int resultSize = reportRepository.getAverageMarkForAllStudentsAllCourses().size();
		  System.out.println(" resultSize " + resultSize);
		  int i,j;
		  int counter = 0;
		 while(counter<resultSize) {
		  for(i=0; i<courseService.getAllCourses().size();i++) {
			  for(j=0; j<studentService.getAllStudents().size();j++) {
					 Report report = new Report(studentService.getAllStudents().get(j).getName(),courseService.getAllCourses().get(i).getName(),reportRepository.getAverageMarkForAllStudentsAllCourses().get(counter));
					 reportList.add(report);
					 counter++;
			  }
		  }
		  
		 }
		return reportList;
	  }
	    

}
