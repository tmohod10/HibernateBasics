package org.example.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hb_employee_tracker?useSSL=false";
        String username = "hbstudent";
        String password = "hbstudent";

        try {
            System.out.println("Connecting to ... " + url);
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to ... " + url);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
