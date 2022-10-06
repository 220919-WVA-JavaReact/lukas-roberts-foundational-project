package com.revature;

import com.revature.models.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.RequestService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EmployeeService es = new EmployeeService();
        RequestService rs = new RequestService();
        int id;
        System.out.println("Press 1 to login. Press 2 to register.");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        Employee loggedInEmployee = null;
        if (choice == 1) {
            loggedInEmployee = es.login();
        } else if (choice == 2) {
            loggedInEmployee = es.register();
        }

        while (loggedInEmployee != null) {
            if (loggedInEmployee.isManager()) {
                System.out.println("----------------------------------------------------------------------------------------------------------");
                System.out.println("Main Menu: Welcome back, " + loggedInEmployee.getFirst() + "!");
                System.out.println("     1) View a specific request(request id required).");
                System.out.println("     2) View all pending requests.");
                System.out.println("     3) Update a request(request id required).");
                System.out.println("     4) View all requests of a specific employee(employee id required).");
                System.out.println("     5) Quit.");
                System.out.println("----------------------------------------------------------------------------------------------------------");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Please enter the id of the request you wish to see.");
                        id = sc.nextInt();
                        rs.getRequestById(id);
                        break;
                    case 2:
                        rs.viewAllOpenRequests(loggedInEmployee);
                        break;
                    case 3:
                        System.out.println("Please enter the id of the request you wish update.");
                        System.out.println("*** Managers can't approve or deny their own requests. ***");
                        id = sc.nextInt();
                        rs.updateRequest(id, loggedInEmployee);
                        break;
                    case 4:
                        System.out.println("What is the id of the employee whose requests you want to see?");
                        id = sc.nextInt();
                        rs.getRequestsByEmployeeId(id);
                        break;
                    case 5:
                        System.out.println("Have a good day, " + loggedInEmployee.getFirst() + "!");
                        loggedInEmployee = null;
                        break;
                }
            } else {
                System.out.println("Main Menu: Welcome back, " + loggedInEmployee.getFirst() + "!");
                System.out.println("     1) Create a reimbursement request.");
                System.out.println("     2) View your pending requests.");
                System.out.println("     3) Quit.");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        rs.createRequest(loggedInEmployee);
                    case 2:
                        rs.viewMyOpenRequests(loggedInEmployee);
                    case 3:
                        System.out.println("Have a good day, " + loggedInEmployee.getFirst() + "!");
                        loggedInEmployee = null;
                        break;
                }
            }
        }
    }
}
