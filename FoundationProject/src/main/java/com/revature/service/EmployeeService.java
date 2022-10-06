package com.revature.service;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;

import java.util.Scanner;

public class EmployeeService {
    EmployeeDAO ed = new EmployeeDAOImplPostgres();
    Scanner sc = new Scanner(System.in);

    public Employee login() {
        System.out.println("Please enter your email.");
        String email = sc.nextLine();
        System.out.println("Please enter your password.");
        String password = sc.nextLine();
        Employee employee = ed.getEmployeeByEmail(email);
        if (password.equals(employee.getPassword())) {
            System.out.println("You have been successfully logged in!");
            return employee;
        } else {
            System.out.println("Login attempt has failed. Please try again.");
            return null;
        }
    }

    public Employee register() {
        System.out.println("Please enter your first name.");
        String first = sc.nextLine();
        System.out.println("Please enter your last name.");
        String last = sc.nextLine();
        System.out.println("Please enter your email address.");
        String email = sc.nextLine();
        System.out.println("Please create a password.");
        String password = sc.nextLine();
        System.out.println("Are you a manager? Press 1 for yes. Press 2 for no.");
        int manager = sc.nextInt();
        boolean bool = manager == 1;
        return ed.createEmployee(first, last, email, password, bool);
    }
}
