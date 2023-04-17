package com.luv2code.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {
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
			
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "WHERE i.id = :instructorId", 
							Instructor.class);
			query.setParameter("instructorId", instructorId);
			
			// execute query and get the instructor
			Instructor instructor = query.getSingleResult();
			
			System.out.println("luv2code: Instructor: " + instructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			// to resolve the Exception
			// option 2: Hibernate query with HQL
			
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
