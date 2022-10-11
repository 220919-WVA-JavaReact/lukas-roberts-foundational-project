package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.EmployeeType;
import com.revature.models.Request;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImplPostgres implements RequestDAO{
    @Override
    public Request createRequest(Employee employee, double price, String description, String type) {
        Request request = new Request();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO requests (employee_id, price, description, type, approval_status, completed) VALUES (?,?,?,?,?,?) RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.setString(4, type);
            ps.setString(5, "Pending");
            ps.setBoolean(6, false);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int receivedId = rs.getInt("id");
                double receivedPrice = rs.getDouble("price");
                String receivedDescription = rs.getString("description");
                String receivedType = rs.getString("type");
                String receivedApproval = rs.getString("approval_status");
                boolean receivedCompleted = rs.getBoolean("completed");
                request = new Request(receivedId, employee, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public List<Request> viewMyPendingRequests(Employee employee) {
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM requests WHERE employee_id = ? AND approval_status = 'Pending' ORDER BY id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int receivedId = rs.getInt("id");
                    double receivedPrice = rs.getDouble("price");
                    String receivedDescription = rs.getString("description");
                    String receivedType = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");
                    boolean receivedCompleted = rs.getBoolean("completed");
                    Request request = new Request(receivedId, employee, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> viewMyRequestsByType(Employee employee, String type) {
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM requests WHERE employee_id = ? AND type = ? ORDER BY id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ps.setString(2, type);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int receivedId = rs.getInt("id");
                    double receivedPrice = rs.getDouble("price");
                    String receivedDescription = rs.getString("description");
                    String receivedType = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");
                    boolean receivedCompleted = rs.getBoolean("completed");
                    Request request = new Request(receivedId, employee, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> viewAllRequests(Employee employee) {
        List<Request> requests = new ArrayList<>();
        Employee emp = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM requests r NATURAL JOIN employees e WHERE r.employee_id != ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int receivedId = rs.getInt("id");
                    double receivedPrice = rs.getDouble("price");
                    String receivedDescription = rs.getString("description");
                    String receivedType = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");
                    boolean receivedCompleted = rs.getBoolean("completed");
                    emp.setId(rs.getInt("employee_id"));
                    emp.setFirst(rs.getString("first"));
                    emp.setLast(rs.getString("last"));
                    emp.setUsername(rs.getString("username"));
                    emp.setPassword(rs.getString("password"));
                    emp.setEmployeeLevel(EmployeeType.valueOf(rs.getString("employee_level")));
                    Request request = new Request(receivedId, emp, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> viewRequestsByStatus(String status) {
        List<Request> requests = new ArrayList<>();
        Employee emp = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM requests NATURAL JOIN employees WHERE approval_status = ? ORDER BY id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int receivedId = rs.getInt("id");
                    double receivedPrice = rs.getDouble("price");
                    String receivedDescription = rs.getString("description");
                    String receivedType = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");
                    boolean receivedCompleted = rs.getBoolean("completed");
                    emp.setId(rs.getInt("employee_id"));
                    emp.setFirst(rs.getString("first"));
                    emp.setLast(rs.getString("last"));
                    emp.setUsername(rs.getString("username"));
                    emp.setPassword(rs.getString("password"));
                    emp.setEmployeeLevel(EmployeeType.valueOf(rs.getString("employee_level")));
                    Request request = new Request(receivedId, emp, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> getRequestsByEmployeeId(int id) {
        Employee emp = new Employee();
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM requests NATURAL JOIN employees WHERE employee_id = ? ORDER BY id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int receivedId = rs.getInt("id");
                    double receivedPrice = rs.getDouble("price");
                    String receivedDescription = rs.getString("description");
                    String receivedType = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");
                    boolean receivedCompleted = rs.getBoolean("completed");
                    emp.setId(rs.getInt("employee_id"));
                    emp.setFirst(rs.getString("first"));
                    emp.setLast(rs.getString("last"));
                    emp.setUsername(rs.getString("username"));
                    emp.setPassword(rs.getString("password"));
                    emp.setEmployeeLevel(EmployeeType.valueOf(rs.getString("employee_level")));
                    Request request = new Request(receivedId, emp, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public Request updateRequest(int id, String approvalStatus, Employee employee) {
        Request request = new Request();
        Employee emp = employee;
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE requests SET approval_status = ?, completed = ? WHERE id = ? RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, approvalStatus);
            ps.setBoolean(2, true);
            ps.setInt(3, id);
            ResultSet rs;
            if((rs = ps.executeQuery()) != null) {
                rs.next();
                int receivedId = rs.getInt("id");
                double receivedPrice = rs.getDouble("price");
                String receivedDescription = rs.getString("description");
                String receivedType = rs.getString("type");
                String receivedApproval = rs.getString("approval_status");
                boolean receivedCompleted = rs.getBoolean("completed");
                request = new Request(receivedId, emp, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public Request getRequestById(int id) {
        Request request = new Request();
        Employee emp = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM requests NATURAL JOIN employees WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int receivedId = rs.getInt("id");
                double receivedPrice = rs.getDouble("price");
                String receivedDescription = rs.getString("description");
                String receivedType = rs.getString("type");
                String receivedApproval = rs.getString("approval_status");
                boolean receivedCompleted = rs.getBoolean("completed");
                emp.setId(rs.getInt("employee_id"));
                emp.setFirst(rs.getString("first"));
                emp.setLast(rs.getString("last"));
                emp.setUsername(rs.getString("username"));
                emp.setPassword(rs.getString("password"));
                emp.setEmployeeLevel(EmployeeType.valueOf(rs.getString("employee_level")));
                request = new Request(receivedId, emp, receivedPrice, receivedDescription, receivedType, receivedApproval, receivedCompleted);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }
}
