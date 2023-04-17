package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 3;
			
			// get a new session and start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("Getting student with id : " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			if (myStudent != null) {
				System.out.println("Get complete: " + myStudent);
				System.out.println("Deleting student:" + myStudent);
				
				session.delete(myStudent);
				
			} else {
				System.out.println("Student is not present with the given id");
			}
			
			
			
			// delete student with id : 2 
			System.out.println("Deleting student id = 2");
			session.createQuery("delete from Student where id = 2").executeUpdate();
			
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}	
