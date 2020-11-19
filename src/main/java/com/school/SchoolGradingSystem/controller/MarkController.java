package com.school.SchoolGradingSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.SchoolGradingSystem.model.ERole;
import com.school.SchoolGradingSystem.model.Mark;
import com.school.SchoolGradingSystem.model.MarkRepository;
import com.school.SchoolGradingSystem.model.UserRepository;
import com.school.SchoolGradingSystem.service.MarkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/marks")
@Api(tags = "Marks")
public class MarkController {
	
	@Autowired
	private MarkService markService;
	
	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@ApiResponses(
            value={
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
 
	@ApiOperation(value ="List of Marks (For teachers only)")
	@GetMapping("/getAllMarks")
	@PreAuthorize("hasRole('TEACHER')")
	public List<Mark> allMarks() {

	    return markService.getAllMarks();
	}
	  
   @ApiOperation(value ="Find mark by id (For teachers only)")
   @GetMapping("/getMark/{id}")
   @PreAuthorize("hasRole('TEACHER')")
   public Mark getMark(@PathVariable("id") long id) {
		return markService.getMark(id);
   }
   
   
   
    @ApiOperation(value ="Return marks for a single student has in a single course (For teachers and students)")  
	@GetMapping("/getMarksForSingleStudentSingleCourse")
	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@ResponseBody
	public List<Mark> getMarksForSingleStudentSingleCourse(@RequestParam(name = "studentName") String studentName, @RequestParam(name="courseName") String courseName) {
    	    
    	List<Mark> singleMarkList = new ArrayList<Mark>();
    	    
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          
        List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
        
        if(roles.get(0).equals(ERole.ROLE_STUDENT.name())) {
        	
        	 if(userDetails.getUsername().equals(studentName)) {
        		 
        		 for(int i=0; i<markRepository.getMarksForSingleStudentSingleCourse(studentName, courseName).size(); i++) {
        	    		
        	    		singleMarkList.add(markService.getMark(markRepository.getMarksForSingleStudentSingleCourse(studentName, courseName).get(i)));
        	    	}
        	 }
        	 
        	 
        }else {
        	
        	for(int i=0; i<markRepository.getMarksForSingleStudentSingleCourse(studentName, courseName).size(); i++) {
        		
        		singleMarkList.add(markService.getMark(markRepository.getMarksForSingleStudentSingleCourse(studentName, courseName).get(i)));
        	}
        	
        }
        	
    	
		return singleMarkList;
	}

	
	@ApiOperation(value ="Return all marks for a single student has across all courses (For teachers and students)")
	@GetMapping("/getMarksForSingleStudentAllCourse")
	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@ResponseBody
	public List<Mark> getMarksForSingleStudentAllCourse(@RequestParam(name = "studentName") String studentName) {
		
		List<Mark> allMarkList = new ArrayList<Mark>();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
        
        if(roles.get(0).equals(ERole.ROLE_STUDENT.name())) {
        	
        	 if(userDetails.getUsername().equals(studentName)) {
        		 
        		 for(int i=0; i<markRepository.getMarksForSingleStudentAllCourse(studentName).size(); i++) {
        	    		
        	        	allMarkList.add(markService.getMark(markRepository.getMarksForSingleStudentAllCourse(studentName).get(i)));
        	    	}
        	 }       	 
        	 
        }else {
        	
        	for(int i=0; i<markRepository.getMarksForSingleStudentAllCourse(studentName).size(); i++) {
        		
            	allMarkList.add(markService.getMark(markRepository.getMarksForSingleStudentAllCourse(studentName).get(i)));
        	}
        	
        }
        
	 
      return allMarkList;
	}
	  

	  
  @ApiOperation(value ="Create a new mark (For teachers only)")
  @PostMapping("/addMark")
  @PreAuthorize("hasRole('TEACHER')")
  public void addMark(@RequestBody Mark mark){
		  
	 markService.addMark(mark);
   }
	  
  @ApiOperation(value ="Update mark (For teachers only)")
  @PutMapping("/updateMark/{id}")
  @PreAuthorize("hasRole('TEACHER')")
  public void updateMark(@RequestBody Mark mark,@PathVariable("id") long id){
		  
	markService.updateMark(id, mark);
  }
	  
  @ApiOperation(value ="Delete mark (For teachers only)")
  @DeleteMapping("/deleteMark/{id}")
  @PreAuthorize("hasRole('TEACHER')")
  public void deleteMark(@PathVariable("id") long id){
		  
	markService.deleteMark(id);
  }

}
