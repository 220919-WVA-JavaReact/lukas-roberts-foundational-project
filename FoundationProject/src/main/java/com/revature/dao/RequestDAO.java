package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.Request;
import java.util.List;

public interface RequestDAO {
    Request createRequest(Employee employee, double price, String description);

    List<Request> viewMyOpenRequests(Employee employee);

    List<Request> viewAllOpenRequests(Employee employee);

    List<Request> getRequestsByEmployeeId(int id);

    Request updateRequest(int id, String approvalStatus);

    Request getRequestById(int id);
}
