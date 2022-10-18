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

    public List<Request> getRequestsByEmployeeId(int employeeId) {
        List<Request> requests = rd.getRequestsByEmployeeId(employeeId);

    }

}
