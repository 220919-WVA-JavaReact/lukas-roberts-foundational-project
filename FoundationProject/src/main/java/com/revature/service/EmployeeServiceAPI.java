package com.revature.service;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;
import com.revature.models.EmployeeType;

import java.util.List;

public class EmployeeServiceAPI {
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

    public Employee getEmployee(String username) {
        return ed.getEmployeeByUsername(username);
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

    public Employee changePassword(Employee employee, String password) {
        return ed.changePassword(employee, password);
    }

    public List<Employee> getEmployeeByLevel(EmployeeType level) {
        return ed.getEmployeeByLevel(level);
    }

    public Employee changeEmployeeLevel(int idEmployeeToChange, EmployeeType level) {
        return ed.changeEmployeeLevel(idEmployeeToChange, level);
    }
}
