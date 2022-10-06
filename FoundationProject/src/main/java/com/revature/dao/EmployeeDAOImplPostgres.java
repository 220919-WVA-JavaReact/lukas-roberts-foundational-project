package com.revature.dao;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

import java.sql.*;

public class EmployeeDAOImplPostgres implements  EmployeeDAO {
    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM employees WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedEmail = rs.getString("email");
                String password = rs.getString("password");
                boolean manager = rs.getBoolean("manager");
                employee = new Employee(id, first, last, receivedEmail, password, manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

// to get all teachers
// List<Teacher> teacher = new ArrayList<>();
//    while (rs.next()) {
//        int id = rs.getInt("teacher_id");
//        String firsrt = rs.getString("first");
//        Teacher teach = new Teacher(id, first...);
//        teachers.add(teach);
//    }

    @Override
    public Employee createEmployee(String first, String last, String email, String password, boolean manager) {
        Employee employee = new Employee();
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employees (first, last, email, password, manager) VALUES (?,?,?,?,?) RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setBoolean(5, manager);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("employee_id");
                String recFirst = rs.getString("first");
                String recLast = rs.getString("last");
                String recEmail = rs.getString("email");
                String recPassword = rs.getString("password");
                Boolean recManager = rs.getBoolean("manager");
                employee = new Employee(id, recFirst, recLast, recEmail, recPassword, recManager);
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
