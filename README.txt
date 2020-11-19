                                                              SCHOOL GRADING API

This Spring Boot Rest API models a basic school grading system. The system objects consist of students, courses and marks.

The API has security layer with Bearer tokens and role based authorization. 

DATABASE
========

H2 database is used in this API. You can reach it from 'http://localhost:8080/h2-console'  link and you can see the details of tables.

While loginning to H2 database you should use these connection informations:
 - Password field should be empty!!

  Driver Class : org.h2.Driver
  JDBC URL     : jdbc:h2:mem:testdb
  User Name    : sa
  Password     :               



TO START TO USE the API
=======================

User should

1- sign up a new account with username,password, email and role via 'Sign-up' method of Registration/Authentication tab in Swagger UI.
   Roles can be "teacher" or "student". By user’s role ("teacher" or "student") we authorize the user to access resources.
   
 !!! When a user signs up with a "student" role, the username must be same with the student_name column(column B) on the CSV file !!!

     For example "Petey Cruiser" is a student so he must sign up with  "username":"Petey Cruiser"  

   After sign up you should get this output as the response:

            "message": "User registered successfully!"


    Example request for teacher;

	{
		"username":"teacher",
		"email":"teacher@school.com",
		"password":"87654321",
		"role":["teacher"]
	}

    Example request for student;

	{
		"username":"Petey Cruiser",
		"email":"peteycruiser@school.com",
		"password":"12341234",
		"role":["student"]
	}


2- login with username & password via 'Sign-in' method of Registration/Authentication tab in Swagger UI.
    After sign in you should get a JWT token and information about the user as the response

    Example request for teacher;
   	{
		"username":"teacher",
		"password":"87654321"
	}

   Example request for student;
	{
		"username":"Petey Cruiser",
		"password":"12341234"
	}

3- click on the 'Authorize button' in Swagger UI.
Enter “Bearer ” with JWT token in the value field and click on Authorize button.


Now you can reach the resources on API according to your role's authorization.


AUTHORIZATION OF ROLES
======================
There are two types of roles in this API. TEACHER role can reach all endpoints of the API but STUDENT role can reach only limited endpoints.

Teachers' endpoints have "(For teachers only)" explanation.

Both teachers' and students' endpoints have "(For teachers and students)" explanation.


STUDENTS CAN ONLY GET THE DATA ABOUT THEMSELVES
===============================================

Students can reach only their personal data and don't have right to access to other students' data.  

A student to avoid accessing another students' data, the username is controlled.

When students want to get data about themselves, their username should be same as their names.


For example "Petey Cruiser" is a student and wants to get data about himself so he must sign up with  "username":"Petey Cruiser"  and then sign in with this name to reach 
his data. And if he wants to get another student's (e.g. Anna Sthesia) data he can't get it.


 
 






