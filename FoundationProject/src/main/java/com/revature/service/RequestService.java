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

    public void createRequest(Employee employee) {
        System.out.println("Enter an amount for reimbursement.");
        double price = sc.nextDouble();
        while (price <= 0) {
            System.out.println("Enter an amount for reimbursement.");
            price = sc.nextDouble();
        }
        sc.nextLine();
        System.out.println("Please describe the reason for your request.");
        String description = sc.nextLine();
        while (description.equals("")) {
            System.out.println("Please describe the reason for your request.");
            description = sc.nextLine();
        }
        sc.nextLine();
        System.out.println("What type of request is this?");
        System.out.println("     1) Travel.");
        System.out.println("     2) Lodging.");
        System.out.println("     3) Food.");
        System.out.println("     4) Other.");
        int choice = sc.nextInt();
        while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
            System.out.println("What type of request is this?");
            System.out.println("     1) Travel.");
            System.out.println("     2) Lodging.");
            System.out.println("     3) Food.");
            System.out.println("     4) Other.");
            choice = sc.nextInt();
        }
        String type;
        if (choice == 1) {
            type = "Travel";
        } else if (choice == 2) {
            type = "Lodging";
        } else if (choice == 3) {
            type = "Food";
        } else {
            type = "Other";
        }
        Request request = rd.createRequest(employee, price, description, type);
        formatRequest(request);
    }

    public void viewMyOpenRequests(Employee employee) {
        List<Request> requests = rd.viewMyPendingRequests(employee);
        if (requests.size() > 0) {
            System.out.println("Pending Requests for Employee: " + employee.getFirst() + " " + employee.getLast());
            for (Request request : requests) {
                System.out.println("      Request Id: " + request.getId());
                System.out.println("      Amount:  $" + request.getPrice());
                System.out.println("      Description: " + request.getDescription());
                System.out.println("      Type: " + request.getType());
                System.out.println("----------------------------------------------------------------------------------------------------------");
            }
        } else {
            System.out.println("You have no pending requests.");
        }
    }

    public void viewMyRequestsByType(Employee employee) {
        System.out.println("What type of requests do you want to see?");
        System.out.println("     1) Travel.");
        System.out.println("     2) Lodging.");
        System.out.println("     3) Food.");
        System.out.println("     4) Other.");
        int choice = sc.nextInt();
        String type;
        if (choice == 1) {
            type = "Travel";
        } else if (choice == 2) {
            type = "Lodging";
        } else if (choice == 3) {
            type = "Food";
        } else {
            type = "Other";
        }
        List<Request> requests = rd.viewMyRequestsByType(employee, type);
        for (Request request : requests) {
            if (requests.size() > 0) {
                formatRequest(request);
            } else {
                System.out.println("You have no requests of type " + type + ".");
            }
        }
    }

    public void viewAllRequests(Employee employee) {
        List<Request> requests = rd.viewAllRequests(employee);
        for (Request request : requests) {
            if (requests.size() > 0) {
                formatRequest(request);
            } else {
                System.out.println("There are no requests.");
            }
        }
    }

    public void viewRequestsByStatus() {
        System.out.println("Please choose a status option below. To quit to main menu press 4.");
        System.out.println("     1) Pending.");
        System.out.println("     2) Approved.");
        System.out.println("     3) Denied.");
        int choice = sc.nextInt();
        String stat;
        if (choice == 1) {
            stat = "Pending";
            List<Request> requests = rd.viewRequestsByStatus(stat);
            for (Request request : requests) {
                if (requests.size() > 0) {
                    formatRequest(request);
                } else {
                    System.out.println("There are no " + request.getApprovalStatus() + " requests.");
                }
            }
        } else if (choice == 2) {
            stat = "Approved";
            List<Request> requests = rd.viewRequestsByStatus(stat);
            for (Request request : requests) {
                if (requests.size() > 0) {
                    formatRequest(request);
                } else {
                    System.out.println("There are no " + request.getApprovalStatus() + " requests.");
                }
            }
        } else if (choice == 3) {
            stat = "Denied";
            List<Request> requests = rd.viewRequestsByStatus(stat);
            for (Request request : requests) {
                if (requests.size() > 0) {
                    formatRequest(request);
                } else {
                    System.out.println("There are no " + request.getApprovalStatus() + " requests.");
                }
            }
        }
    }

    public void getRequestsByEmployeeId(int employeeId) {
        List<Request> requests = rd.getRequestsByEmployeeId(employeeId);
        for (Request request : requests) {
            formatRequest(request);
        }
    }

    public void updateRequest(Employee employee) {
        System.out.println("Please enter the id of the request you wish update.");
        System.out.println("*** Managers can't approve or deny their own requests. ***");
        int id = sc.nextInt();
        Request request = rd.getRequestById(id);
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
                if (!request.getApprovalStatus().equals("Pending")) {
                    System.out.println(request);
                    System.out.println("Request has been updated successfully");
                }
            } else {
                System.out.println("Nice try :) Managers are not allowed to approve or deny their own requests.");
            }
        } else {
            System.out.println("That request has already been " + request.getApprovalStatus() + ". No further action required.");
        }
    }

    public void getRequestById() {
        System.out.println("Please enter the id of the request you wish to see.");
        int id = sc.nextInt();
        Request request = rd.getRequestById(id);
        formatRequest(request);
    }

    public void formatRequest(Request request) {
        System.out.println("      Request Id: " + request.getId() + "  Employee: " + request.getEmployee().getFirst() + " " + request.getEmployee().getLast());
        System.out.println("      Reimbursement Amount: $" + request.getPrice());
        System.out.println("      Request Description: " + request.getDescription());
        System.out.println("      Request Type: " + request.getType());
        System.out.println("      Status of Request: " + request.getApprovalStatus());
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
}
