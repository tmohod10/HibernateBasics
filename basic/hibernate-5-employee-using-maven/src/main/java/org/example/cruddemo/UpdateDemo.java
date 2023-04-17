package org.example.cruddemo;

import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;

public class UpdateDemo {
    public static void main(String[] args) {

        SessionFactory factory = null;
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            int employeeId = 1;

            Employee theEmployee = session.get(Employee.class, employeeId);

            if (theEmployee == null) {
                System.out.println("Employee with employee id " + employeeId + " does not exists");
            } else {
                System.out.println(theEmployee);
//                theEmployee.setFirstName("Paul");
//                theEmployee.setLastName("Wall");
//                theEmployee.setCompany("Apple");
//                session.save(theEmployee);

                String sqlQuery = "UPDATE Employee " +
                        "SET firstName = :first_name, lastName = :last_name" +
                        " WHERE id = :id";
                Query query = session.createQuery(sqlQuery);
                query.setParameter("first_name", "Mary");
                query.setParameter("last_name", "Public");
                query.setParameter("id", 3);

                int rowsUpdated = query.executeUpdate();
                System.out.println(rowsUpdated);
            }

            session.getTransaction().commit();

        } finally {
            factory.close();
        }

    }
}
