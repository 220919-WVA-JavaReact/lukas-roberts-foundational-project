package com.revature.dao;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

import java.sql.*;

public class EmployeeDAOImplPostgres implements  EmployeeDAO {
    @Override
    public Employee getEmployeeByUsername(String username) {
        Employee employee = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM employees WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String password = rs.getString("password");
                boolean manager = rs.getBoolean("manager");
                employee = new Employee(id, first, last, receivedUsername, password, manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee createEmployee(String first, String last, String username, String password) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employees (first, last, username, password, manager) VALUES (?,?,?,?,?) RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setBoolean(5, false);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean receivedManager = rs.getBoolean("manager");
                return new Employee(id, receivedFirst, receivedLast, receivedUsername, receivedPassword, receivedManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
