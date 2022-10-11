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
                int receivedId = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedAddress1 = rs.getString("address_1");
                String receivedAddress2 = rs.getString("address_2");
                String receivedCity = rs.getString("city");
                String receivedState = rs.getString("state");
                int receivedZip = rs.getInt("zip");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean receivedManager = rs.getBoolean("manager");
                employee = new Employee(receivedId, receivedFirst, receivedLast, receivedAddress1, receivedAddress2, receivedCity, receivedState, receivedZip, receivedUsername, receivedPassword, receivedManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    @Override
    public Employee createEmployee(String first, String last, String address_1, String address_2, String city, String state, int zip, String username, String password) {
        Employee employee = new Employee();
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employees (first, last, address_1, address_2, city, state, zip, username, password, manager) VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, address_1);
            ps.setString(4, address_2);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setInt(7, zip);
            ps.setString(8, username);
            ps.setString(9, password);
            ps.setBoolean(10, false);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int receivedId = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedAddress1 = rs.getString("address_1");
                String receivedAddress2 = rs.getString("address_2");
                String receivedCity = rs.getString("city");
                String receivedState = rs.getString("state");
                int receivedZip = rs.getInt("zip");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean receivedManager = rs.getBoolean("manager");
                employee = new Employee(receivedId, receivedFirst, receivedLast, receivedAddress1, receivedAddress2, receivedCity, receivedState, receivedZip, receivedUsername, receivedPassword, receivedManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    @Override
    public Employee updateEmployeeAddress(int employeeId, String address_1, String address_2, String city, String state, int zip) {
        Employee employee = new Employee();
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE employees set address_1 = ?, address_2 = ?, city = ?, state = ?, zip = ? WHERE employee_id = ? RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, address_1);
            ps.setString(2, address_2);
            ps.setString(3, city);
            ps.setString(4, state);
            ps.setInt(5, zip);
            ps.setInt(6, employeeId);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int receivedId = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedAddress1 = rs.getString("address_1");
                String receivedAddress2 = rs.getString("address_2");
                String receivedCity = rs.getString("city");
                String receivedState = rs.getString("state");
                int receivedZip = rs.getInt("zip");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean receivedManager = rs.getBoolean("manager");
                employee = new Employee(receivedId, receivedFirst, receivedLast, receivedAddress1, receivedAddress2, receivedCity, receivedState, receivedZip, receivedUsername, receivedPassword, receivedManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    @Override
    public Employee changePassword(Employee employee, String password) {
        Employee newEmployee = new Employee();
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE employees set password = ? WHERE employee_id = ? RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(6, employee.getId());
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int receivedId = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedAddress1 = rs.getString("address_1");
                String receivedAddress2 = rs.getString("address_2");
                String receivedCity = rs.getString("city");
                String receivedState = rs.getString("state");
                int receivedZip = rs.getInt("zip");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean receivedManager = rs.getBoolean("manager");
                newEmployee = new Employee(receivedId, receivedFirst, receivedLast, receivedAddress1, receivedAddress2, receivedCity, receivedState, receivedZip, receivedUsername, receivedPassword, receivedManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return newEmployee;
    }
}
