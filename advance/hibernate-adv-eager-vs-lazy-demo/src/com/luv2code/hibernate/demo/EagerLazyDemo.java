package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from the db
			int instructorId = 1;
			Instructor instructor = session.get(Instructor.class , instructorId);
			
			System.out.println("luv2code: Instructor: " + instructor);
			
//			if (instructor != null) {
//				// get course of the instructor
//				System.out.println("luv2code: Courses: " + instructor.getCourses());
//			}
			
			System.out.println("luv2code: Courses: " + instructor.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			// to resolve the Exception
			// Option 1: call the getter method while the session is still open
			
			// since courses are lazy loaded ... this should fail
			if (instructor != null) {
				// get course of the instructor
				System.out.println("luv2code: the session is now closed ...");
				System.out.println("luv2code: Courses: " + instructor.getCourses());
			}
			
			System.out.println("luv2code: Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}	
