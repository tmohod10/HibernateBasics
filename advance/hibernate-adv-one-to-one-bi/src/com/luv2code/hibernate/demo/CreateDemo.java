package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// create the objects
			Instructor theInstructor = 
					new Instructor("Chad", "Darby", "darby@luv2code.com");
			
			InstructorDetail theInstructorDetail = 
					new InstructorDetail(
							"https://www.luv2code.com/youtube",
							"Luv 2 code!!!");
			
//			Instructor theInstructor = 
//					new Instructor("Madhu", "Patel", "madhu@luv2code.com");
//			
//			InstructorDetail theInstructorDetail = 
//					new InstructorDetail(
//							"https://www.youtube.com",
//							"Guitar");
			
			// associate the objects
			theInstructor.setInstructorDetail(theInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			// Note: this will ALSO save the details object
			// because of the CascadeType.ALL
			System.out.println("Saving instructor: " + theInstructor);
			session.save(theInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}	
