package com.revature.dao;

import com.revature.models.Employee;

public interface EmployeeDAO {
    Employee getEmployeeByEmail(String email);

    Employee createEmployee(String first, String last, String email, String password, boolean manager);
}
