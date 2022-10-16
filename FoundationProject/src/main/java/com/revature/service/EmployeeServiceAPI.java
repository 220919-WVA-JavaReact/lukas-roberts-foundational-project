package com.revature.service;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;
import com.revature.models.EmployeeType;

import java.util.List;

public class EmployeeServiceAPI {
//    public EmployeeServiceAPI(EmployeeDAOImplPostgres employeeDAOImplPostgres) {
//        System.out.println("Employee Service Initialized!");
//    }
    EmployeeDAO ed = new EmployeeDAOImplPostgres();

    public String login(String username, String password) {
        boolean userExists = ed.usernameInSystem(username);
        if(!userExists) {
            return "username";
        }
        Employee employee = ed.getEmployeeByUsername(username);
        if (password.equals(employee.getPassword())) {
            System.out.println("You have been successfully logged in!");
            return employee.toJsonString();
        } else {
            System.out.println("Login attempt has failed. Please try again.");
            return "password";
        }
    }

    public Employee register(String first, String last, String address1, String unit, String city, String state, int zip, String username, String password) {
        return ed.createEmployee(first, last, address1, unit, city, state, zip, username, password);
    }

    public List<Employee> getAllEmployees(Employee loggedInEmployee) {
        return ed.getAllEmployees(loggedInEmployee);
    }
    public Employee updateEmployeeAddress(int id, String address1, String address2, String city, String state, int zip) {
        return ed.updateEmployeeAddress(id, address1, address2, city, state, zip);
    }

    public Employee changePassword(Employee employee) {
        return null;
    }

    public List<Employee> getEmployeeByLevel(EmployeeType level) {
        return ed.getEmployeeByLevel(level);
    }

    public Employee changeEmployeeLevel(Employee loggedIneEmployee, Employee employeeToChange, EmployeeType level) {
        return null;
    }
}
