package org.example.cruddemo;

import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class ReadDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        int employeeId = 40;

        Employee theEmployee = session.get(Employee.class, employeeId);

        if (theEmployee != null) {
            System.out.println(theEmployee);
        } else {
            System.out.println("Employee doesn't exists");
        }

        List<Employee> theEmployeeList = session.createQuery("from Employee where id = 4454").getResultList();

        for (Employee emp : theEmployeeList) {
            System.out.println(theEmployeeList);
        }
    }
}
