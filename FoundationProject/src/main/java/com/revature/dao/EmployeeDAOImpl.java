package com.revature.dao;

import com.revature.models.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDAOImpl implements EmployeeDAO {
    String line = "";
    String splitBy = ",";
    @Override
    public Employee getEmployeeByEmail(String email) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
            while ((line = br.readLine()) != null) {
                String[] info = line.split(splitBy);
                if (info[3].equals(email)) {
                    int id = Integer.parseInt(info[0]);
                    boolean manager = false;
                    if (info[5].equals("true")) {
                        manager = true;
                    }
                    Employee employee = new Employee(id,info[1], info[2],info[3], info[4], manager);
                    employee.setId(id);
                    return employee;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employee createEmployee(String first, String last, String email, String password, boolean manager) {
        int id = 0;
        return new Employee(id, first, last, email, password, manager);
    }
}
