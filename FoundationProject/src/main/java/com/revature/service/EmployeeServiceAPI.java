package com.revature.service;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;

public class EmployeeServiceAPI {
    public EmployeeServiceAPI() {
        System.out.println("Employee Service Initialized!");
    }
    EmployeeDAO ed = new EmployeeDAOImplPostgres();

    public Employee login(String username, String password) {
        Employee employee = ed.getEmployeeByUsername(username);
        if (password.equals(employee.getPassword())) {
            System.out.println("You have been successfully logged in!");
            return employee;
        } else {
            System.out.println("Login attempt has failed. Please try again.");
            return null;
        }
    }

    public Employee register(String first, String last, String address1, String unit, String city, String state, int zip, String username, String password) {
        return ed.createEmployee(first, last, address1, unit, city, state, zip, username, password);
    }
}
