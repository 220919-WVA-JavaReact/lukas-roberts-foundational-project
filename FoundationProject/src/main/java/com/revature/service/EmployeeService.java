package com.revature.service;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;

import java.util.Scanner;

public class EmployeeService {
    EmployeeDAO ed = new EmployeeDAOImplPostgres();
    Scanner sc = new Scanner(System.in);

    public Employee login() {
        System.out.println("Please enter your username.");
        String username = sc.nextLine();
        System.out.println("Please enter your password.");
        String password = sc.nextLine();
        Employee employee = ed.getEmployeeByUsername(username);
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
        while (first.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please enter your first name.");
            first = sc.nextLine();
        }
        System.out.println("Please enter your last name.");
        String last = sc.nextLine();
        while (last.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please enter your last name.");
            last = sc.nextLine();
        }
        System.out.println("Please enter your street address.");
        String address1 = sc.nextLine();
        while (address1.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please enter your street address.");
            address1 = sc.nextLine();
        }
        sc.nextLine();
        System.out.println("If you have a unit number, press 1. If you have an apartment number, press 2.");
        System.out.println("Press 3 if you have neither.");
        int choice = sc.nextInt();
        while (choice !=1 && choice != 2 && choice != 3) {
            System.out.println("Invalid option.");
            System.out.println("If you have a unit number, press 1. If you have an apartment number, press 2.");
            System.out.println("Press 3 if you have neither.");
            choice = sc.nextInt();
        }
        String unit;
        if (choice == 1) {
            System.out.println("What is your unit number?");
            unit = "Unit " + sc.nextLine();
            while (unit.equals("")) {
                System.out.println("The field cannot be empty.");
                System.out.println("What is your unit number?");
                unit = "Unit " + sc.nextLine();
            }
        } else if (choice == 2) {
            System.out.println("What is your apartment number?");
            unit = "Apt " + sc.nextLine();
            while (unit.equals("")) {
                System.out.println("The field cannot be empty.");
                System.out.println("What is your apartment number?");
                unit = "Unit " + sc.nextLine();
            }
        } else {
            unit = "N/A";
        }
        System.out.println("In which city do you live?");
        String city = sc.nextLine();
        while (city.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("In which city do you live?");
            city = sc.nextLine();
        }
        System.out.println("In which state do you live?");
        String state = sc.nextLine();
        while (state.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("In which state do you live?");
            state = sc.nextLine();
        }
        System.out.println("What is your ZIP code?");
        int zip = sc.nextInt();
        System.out.println("Please create a username.");
        String username = sc.nextLine();
        while (username.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please create a username.");
            username = sc.nextLine();
        }
        System.out.println("Please create a password.");
        String password = sc.nextLine();
        while (password.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please create a password.");
            password = sc.nextLine();
        }
        return ed.createEmployee(first, last, address1, unit, city, state, zip, username, password);
    }

    public Employee updateEmployeeAddress(Employee employee) {
        System.out.println("Please enter your street address.");
        String address1 = sc.nextLine();
        while (address1.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please enter your street address.");
            address1 = sc.nextLine();
        }
        sc.nextLine();
        System.out.println("If you have a unit number, press 1. If you have an apartment number, press 2.");
        System.out.println("Press 3 if you have neither.");
        int choice = sc.nextInt();
        while (choice !=1 && choice != 2 && choice != 3) {
            System.out.println("Invalid option.");
            System.out.println("If you have a unit number, press 1. If you have an apartment number, press 2.");
            System.out.println("Press 3 if you have neither.");
            choice = sc.nextInt();
        }
        String unit;
        if (choice == 1) {
            System.out.println("What is your unit number?");
            unit = "Unit " + sc.nextLine();
            while (unit.equals("")) {
                System.out.println("The field cannot be empty.");
                System.out.println("What is your unit number?");
                unit = "Unit " + sc.nextLine();
            }
        } else if (choice == 2) {
            System.out.println("What is your apartment number?");
            unit = "Apt " + sc.nextLine();
            while (unit.equals("")) {
                System.out.println("The field cannot be empty.");
                System.out.println("What is your apartment number?");
                unit = "Unit " + sc.nextLine();
            }
        } else {
            unit = "N/A";
        }
        System.out.println("In which city do you live?");
        String city = sc.nextLine();
        while (city.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("In which city do you live?");
            city = sc.nextLine();
        }
        System.out.println("In which state do you live?");
        String state = sc.nextLine();
        while (state.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("In which state do you live?");
            state = sc.nextLine();
        }
        System.out.println("What is your ZIP code?");
        int zip = sc.nextInt();
        System.out.println("Please create a username.");
        String username = sc.nextLine();
        while (username.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please create a username.");
            username = sc.nextLine();
        }

        return ed.updateEmployeeAddress(employee.getId(), address1, unit, city, state, zip);
    }

    public Employee changePassword(Employee employee) {
        System.out.println("Please enter a new password.");
        String password = sc.nextLine();
        while (password.equals("")) {
            System.out.println("The field cannot be empty.");
            System.out.println("Please enter a new password.");
            password = sc.nextLine();
        }
        return ed.changePassword(employee, password);
    }
}
