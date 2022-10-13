package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.EmployeeType;

import java.util.List;
import java.util.logging.Level;

public interface EmployeeDAO {
    Employee getEmployeeByUsername(String username);

    Employee createEmployee(String first, String last, String address_1, String address_2, String city, String state, int zip, String username, String password);

    Employee updateEmployeeAddress(int employeeId, String address1, String unit, String city, String state, int zip);

    Employee changePassword(Employee employee, String password);

    List<Employee> getEmployeeByLevel(EmployeeType level);

    Employee changeEmployeeLevel(int id, EmployeeType level);
}
