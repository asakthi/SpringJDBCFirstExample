package com.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentApplication {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		StudentDAO studentDAO = context.getBean("studentDAO", StudentDAO.class);

		Student student = new Student();
		student.setSno(8);
		student.setSname("skteorge");
		student.setSemail("skeorge@gmail.com");
		student.setSmobile("100888889911");

		studentDAO.insertStudent(student);

		student.setSno(2);
		student.setSemail("seorge@gmail.com");
		student.setSmobile("7777770");
		studentDAO.updateStudent(student);

		
		List<Student> studentsList = studentDAO.getStudent();
		for (Student student1 : studentsList) {
			System.out.print(student1.getSno() + "\t");
			System.out.print(student1.getSname() + "\t");
			System.out.print(student1.getSemail() + "\t");
			System.out.println(student1.getSmobile() + "\t");
		}
		
		List <String> list = studentDAO.getStudentMobileNos();		
		System.out.println(list);
		
		System.out.println(studentDAO.getStudentsCount());
	}
}
