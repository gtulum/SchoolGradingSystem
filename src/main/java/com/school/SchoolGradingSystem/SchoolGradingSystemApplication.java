package com.school.SchoolGradingSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.opencsv.CSVReader;
import com.school.SchoolGradingSystem.model.Course;
import com.school.SchoolGradingSystem.model.CourseRepository;
import com.school.SchoolGradingSystem.model.ERole;
import com.school.SchoolGradingSystem.model.Mark;
import com.school.SchoolGradingSystem.model.MarkRepository;
import com.school.SchoolGradingSystem.model.Role;
import com.school.SchoolGradingSystem.model.Student;
import com.school.SchoolGradingSystem.model.StudentCourse;
import com.school.SchoolGradingSystem.model.StudentCourseRepository;
import com.school.SchoolGradingSystem.model.StudentRepository;
import com.school.SchoolGradingSystem.model.RoleRepository;


@SpringBootApplication
public class SchoolGradingSystemApplication implements CommandLineRunner{
  
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	private static final Logger log = LoggerFactory.getLogger(SchoolGradingSystemApplication.class);


	public static void main(String[] args) {
	
		SpringApplication app = new SpringApplication(SchoolGradingSystemApplication.class);
        app.run(args);
	}
				
	@Override
    public void run(String... args) throws Exception {	
	
		FileInputStream fis = null;

        try {
         
        	 Role user = new Role(ERole.ROLE_TEACHER);
			 roleRepository.save(user);
			 user = new Role(ERole.ROLE_STUDENT);
			 roleRepository.save(user);
		 
        	
        	String fileName = "src/main/resources/marks.csv";
            fis = new FileInputStream(new File(fileName));
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] nextLine;
            reader.readNext();
            
            while ((nextLine = reader.readNext()) != null) {
            	
                Student student = new Student(Long.valueOf(nextLine[0]),nextLine[1]);
                                
                StudentCourse studentCourse = new StudentCourse(Long.valueOf(nextLine[0]),nextLine[1],Long.valueOf(nextLine[2]),nextLine[3], Long.valueOf(nextLine[4]),Double.valueOf(nextLine[5]));
                
                Course  course = new Course(Long.valueOf(nextLine[2]),nextLine[3]);
                
                Mark mark = new Mark(Long.valueOf(nextLine[4]),Double.valueOf(nextLine[5]),nextLine[6],nextLine[1],nextLine[3]);
                
                courseRepository.save(course);
                studentRepository.save(student);
                studentCourse.takeCourse(course);
                studentCourseRepository.save(studentCourse);
                markRepository.save(mark);               
            }
          
 
        } catch (FileNotFoundException ex) {
        	log.info("File not found!");
        } catch (IOException ex) {
        	log.info("There are some problems!");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
            	log.info("The file can not be closed!");
            	
            }
        }
    
	  }

}
