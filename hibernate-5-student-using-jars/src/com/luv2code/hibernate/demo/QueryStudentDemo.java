package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// we use Hibernate Query Language (HQL) to run the SQL query
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			// query students: lastName = 'Doe'
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
			
			// display the students
			System.out.println("Students whose last name is Doe");
			displayStudents(theStudents);
			
			
			// query students: lastName = 'Doe' or 'Daffy'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();
			
			// display the students
			System.out.println("Students whose last name is Doe or first Name is Daffy");
			displayStudents(theStudents);
			
			
			// query students: email is luv2code.com
			theStudents = session.createQuery("from Student s where"
							+ " s.email LIKE '%luv2code.com.com'").getResultList();
			
			// display the students
			System.out.println("Students whose email is luv2code.com");
			displayStudents(theStudents);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}	
