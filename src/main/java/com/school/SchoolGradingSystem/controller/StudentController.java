package com.school.SchoolGradingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.SchoolGradingSystem.model.Student;
import com.school.SchoolGradingSystem.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/students")
@Api(tags = "Students")
public class StudentController {
	
  @Autowired
  private StudentService studentService;  
  
  @ApiResponses(
          value={
                  @ApiResponse(code = 403, message = "Forbidden"),
                  @ApiResponse(code = 404, message = "Not found"),
                  @ApiResponse(code = 500, message = "Server error")
          }
  )

  
  @ApiOperation(value ="List of Students (For teachers only)")
  @GetMapping("/getAllStudents")
  @PreAuthorize("hasRole('TEACHER')")
  public List<Student> allUsers() {

      return studentService.getAllStudents();
  }
  
  @ApiOperation(value ="Find student by id (For teachers only)")
  @GetMapping("/getStudentById/{studentId}")
  @PreAuthorize("hasRole('TEACHER')")
  public Student getStudentById(@PathVariable("studentId") long studentId) {
		return studentService.getStudentById(studentId);
		
 }
  
  @ApiOperation(value ="Find student by name (For teachers only)")
  @GetMapping("/getStudentByName/{name}")
  @PreAuthorize("hasRole('TEACHER')")
  public List<Student> getStudentByName(@PathVariable("name") String name) {
		return studentService.getStudentByName(name);
		
  }
  
  @ApiOperation(value ="Create a new student (For teachers only)")
  @PostMapping("/addStudent")
  @PreAuthorize("hasRole('TEACHER')")
  public void addStudent(@RequestBody Student student){
	  
	  studentService.addStudent(student);
  }
  
  @ApiOperation(value ="Update student (For teachers only)")
  @PutMapping("/updateStudent/{id}")
  @PreAuthorize("hasRole('TEACHER')")
  public void updateStudent(@RequestBody Student student,@PathVariable("id") long id){
	  
	  studentService.updateStudent(id, student);
  }
  
  @ApiOperation(value ="Delete student (For teachers only)")
  @DeleteMapping("/deleteStudent/{id}")
  @PreAuthorize("hasRole('TEACHER')")
  public void deleteStudent(@PathVariable("id") long id){
	  
	  studentService.deleteStudent(id);
  }

}
