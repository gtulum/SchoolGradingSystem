package com.school.SchoolGradingSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.SchoolGradingSystem.model.Student;
import com.school.SchoolGradingSystem.model.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents()
    {
        List<Student> studentList = (List<Student>) studentRepository.findAll();
         
        if(studentList.size() > 0) {
            return studentList;
        } else {
            return new ArrayList<Student>();
        }
    }
	
	public Student getStudentById(long studentId) {
		return studentRepository.findById(studentId);
	}
	
	public List<Student> getStudentByName(String name) {
		return studentRepository.findByName(name);
	}
	
	public void addStudent(Student student) {
		studentRepository.save(student);
	}
	
   public void updateStudent(long id,Student student) {
		
	   Student st = studentRepository.findById(id);	
	   st.setName(student.getName());
	   studentRepository.save(st);		         
	}
   
   public void deleteStudent(long id) {
	   studentRepository.deleteById(id);
   }

	
	
}
