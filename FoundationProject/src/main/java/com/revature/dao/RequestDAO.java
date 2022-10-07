package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.Request;
import java.util.List;

public interface RequestDAO {
    Request createRequest(Employee employee, double price, String description);

    List<Request> viewMyPendingRequests(Employee employee);

    List<Request> viewAllRequests(Employee employee);

    List<Request> viewRequestsByStatus(String status);

    List<Request> getRequestsByEmployeeId(int id);

    Request updateRequest(int id, String approvalStatus, Employee employee);

    Request getRequestById(int id);
}
