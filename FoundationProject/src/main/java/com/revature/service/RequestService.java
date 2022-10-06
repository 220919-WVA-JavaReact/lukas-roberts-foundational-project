package com.revature.service;
import com.revature.dao.RequestDAO;
import com.revature.dao.RequestDAOImplPostgres;
import com.revature.models.Employee;
import com.revature.models.Request;
import java.util.List;
import java.util.Scanner;

public class RequestService {
    Scanner sc = new Scanner(System.in);
    RequestDAO rd = new RequestDAOImplPostgres();

    public Request createRequest(Employee employee) {
        System.out.println("Enter an amount for reimbursement.");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Please describe the reason for your request.");
        String description = sc.nextLine();
        Request request = rd.createRequest(employee, price, description);
        request.setEmployee(employee);
        return request;
    }

    public void viewMyOpenRequests(Employee employee) {
        List<Request> requests = rd.viewMyOpenRequests(employee);
        if (requests.size() > 0) {
            for (Request request : requests) {
                System.out.println(request);
            }
        } else {
            System.out.println("You have no pending requests.");
        }

    }

    public void viewAllOpenRequests(Employee employee) {
        List<Request> requests = rd.viewAllOpenRequests(employee);
        for (Request request : requests) {
            System.out.println(request.getEmployee().getFirst() + " " + request.getEmployee().getLast() + ":  $" + request.getPrice() + " : " + request.getDescription());
        }
    }

    public void getRequestsByEmployeeId(int employeeId) {
        List<Request> requests = rd.getRequestsByEmployeeId(employeeId);
        for (Request request : requests) {
            System.out.println(request.getApprovalStatus() + " reimbursement request from: " + request.getEmployee().getFirst() + " " + request.getEmployee().getLast());
            System.out.println("        Amount of request: $" + request.getPrice());
            System.out.println("        Reason for request: " + request.getDescription());
            System.out.println("----------------------------------------------------------------------------------------------------------");
        }
    }

    public Request updateRequest(int id, Employee employee) {
        Request request = getRequestById(id);
        if (request.getApprovalStatus().equals("Pending")) {
            if (request.getEmployee().getId() != employee.getId()) {
                String approvalStatus = "Denied";
                System.out.println("     1) Approve.");
                System.out.println("     2) Deny.");
                int status = sc.nextInt();
                if (status == 1) {
                    approvalStatus = "Approved";
                }
                request = rd.updateRequest(id, approvalStatus, request.getEmployee());
            } else {
                System.out.println("Nice try :) Managers are not allowed to approve or deny their own requests.");
            }
        } else {
            System.out.println("That request has already been dealt with.");
        }
        return null;
    }

    public Request getRequestById(int id) {
        Request request = rd.getRequestById(id);
        System.out.println(request.getApprovalStatus() + " reimbursement request from: " + request.getEmployee().getFirst() + " " + request.getEmployee().getLast());
        System.out.println("        Amount of request: $" + request.getPrice());
        System.out.println("        Reason for request: " + request.getDescription());
        return request;
    }
}
