package org.example.cruddemo;

import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;

public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory factory = null;
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();

            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            int employeeId = 4;
//            Employee theEmployee = session.get(Employee.class, employeeId);

//            if (theEmployee == null) {
//                System.out.println("Employee with employee id " + employeeId + " doesnt exist.");
//            } else {
//                session.delete(theEmployee);

                String sqlQuery = "DELETE FROM Employee WHERE id = :employeeId";
                Query query = session.createQuery(sqlQuery);
                query.setParameter("employeeId", 2);
                int rowAffected = query.executeUpdate();
                System.out.println(rowAffected);

//            }

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
