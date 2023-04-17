package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			// get a new session and start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("Getting student with id : " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			if (myStudent != null) {
				System.out.println("Get complete: " + myStudent);
				System.out.println("Updating student ...");
				
				myStudent.setFirstName("Scooby");
				
			} else {
				System.out.println("Student is not present with the given id");
			}
			
			// commit the transaction
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Update emails for all students");
			session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}	
