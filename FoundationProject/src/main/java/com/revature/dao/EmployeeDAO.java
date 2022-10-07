package com.revature.dao;

import com.revature.models.Employee;

public interface EmployeeDAO {
    Employee getEmployeeByUsername(String username);

    Employee createEmployee(String first, String last, String username, String password);
}
