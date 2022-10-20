package com.revature.service;

import com.revature.dao.RequestDAO;
import com.revature.dao.RequestDAOImplPostgres;
import com.revature.models.Employee;
import com.revature.models.Request;

import java.util.List;

public class RequestServiceAPI {

    RequestDAO rd = new RequestDAOImplPostgres();
    public Request createRequest(Employee employee, double price, String description, String type) {
        return rd.createRequest(employee, price, description, type);
    }

    public List<Request> getMyTickets(int id) {
        return rd.getRequestsByEmployeeId(id);
    }

    public List<Request> getTicketsByStatus(String status) {
        return rd.viewRequestsByStatus(status);
    }

    public Request updateRequest(int id, String approvalStatus) {
        Employee employee = rd.getRequestById(id).getEmployee();
        return rd.updateRequest(id, approvalStatus, employee);
    }

    public Request getTicketById(int id) {
        return rd.getRequestById(id);
    }
}
