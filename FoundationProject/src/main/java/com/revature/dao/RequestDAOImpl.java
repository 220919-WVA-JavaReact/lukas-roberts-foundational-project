package com.revature.dao;
import com.revature.models.Employee;
import com.revature.models.Request;

import java.util.LinkedList;
import java.util.List;

public class RequestDAOImpl implements RequestDAO {
    @Override
    public Request createRequest(Employee employee, double price, String description) {
        return new Request(price, description, employee);
    }

    @Override
    public List<Request> viewMyOpenRequests(Employee employee) {
        return null;
    }

    @Override
    public List<Request> viewAllOpenRequests(Employee employee) {
        // maybe use LinkedList for first in first out for requests
        return null;
    }

    @Override
    public List<Request> getRequestsByEmployeeId(int id) {
        return null;
    }

    @Override
    public Request updateRequest(int id, String approvalStatus) {
        return null;
    }

    @Override
    public Request getRequestById(int id) {
        return null;
    }


}
