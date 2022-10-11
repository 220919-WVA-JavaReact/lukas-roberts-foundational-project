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
        boolean running = true;
        System.out.println("Welcome To Reimburse+");
        while (running) {
            System.out.println("Welcome! Please sign up or login by following the prompts below.");
            System.out.println();
            System.out.println("      To register, press 1.");
            System.out.println("      To log in, press 2.");
            System.out.println();
            System.out.println("Press 3 to quit.");
            System.out.println("----------------------------------------------------------------------------------------------------------");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid option. Menu options are selected by entering the number of the option you wish to select.");
                System.out.println();
                System.out.println("      To register, press 1.");
                System.out.println("      To log in, press 2.");
                System.out.println();
                System.out.println("Press 3 to quit.");
                System.out.println("----------------------------------------------------------------------------------------------------------");
                choice = sc.nextInt();
            }
            sc.nextLine();
            Employee loggedInEmployee = null;
            if (choice == 1) {
                loggedInEmployee = es.register();
                System.out.println("Hello, " + loggedInEmployee.getFirst() + "! Thank you for joining the team!");
            }else if (choice == 2) {
                loggedInEmployee = es.login();
                System.out.println("Welcome back, " + loggedInEmployee.getFirst() + "!");
            }
            else if (choice == 3) {
                System.out.println("Thank you for using Reimburse+. Have a wonderful day!");
                running = false;
            }

            while (loggedInEmployee != null) {
                if (loggedInEmployee.isManager()) {
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Current User: " + loggedInEmployee.getFirst() + " " + loggedInEmployee.getLast());
                    System.out.println();
                    System.out.println("Main Menu:");
                    System.out.println();
                    System.out.println("     1) View a specific request(request id required).");
                    System.out.println("     2) View all requests.");
                    System.out.println("     3) View requests by status.");
                    System.out.println("     4) Update a request(request id required).");
                    System.out.println("     5) View all requests of a specific employee(employee id required).");
                    System.out.println("     6) Update your address.");
                    System.out.println("     7) Change your password.");
                    System.out.println();
                    System.out.println("Press 8 to logout.");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    choice = sc.nextInt();
                    while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 && choice != 8) {
                        System.out.println("Invalid Option.");
                        System.out.println();
                        System.out.println("     1) View a specific request(request id required).");
                        System.out.println("     2) View all pending requests.");
                        System.out.println("     3) View all requests by status.");
                        System.out.println("     4) Update a request(request id required).");
                        System.out.println("     5) View all requests of a specific employee(employee id required).");
                        System.out.println("     6) Update your address.");
                        System.out.println("     7) Change your password.");
                        System.out.println();
                        System.out.println("Press 8 to logout.");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        choice = sc.nextInt();
                    }
                    switch (choice) {
                        case 1:
                            rs.getRequestById();
                            break;
                        case 2:
                            rs.viewAllRequests(loggedInEmployee);
                            break;
                        case 3:
                            rs.viewRequestsByStatus();
                            break;
                        case 4:
                            rs.updateRequest(loggedInEmployee);
                            break;
                        case 5:
                            System.out.println("What is the id of the employee whose requests you want to see?");
                            id = sc.nextInt();
                            rs.getRequestsByEmployeeId(id);
                            break;
                        case 6:
                            es.updateEmployeeAddress(loggedInEmployee);
                            break;
                        case 7:
                            es.changePassword(loggedInEmployee);
                            break;
                        case 8:
                            System.out.println("Have a good day, " + loggedInEmployee.getFirst() + "!");
                            loggedInEmployee = null;
                            break;
                    }
                } else {
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Current User: " + loggedInEmployee.getFirst() + " " + loggedInEmployee.getLast());
                    System.out.println();
                    System.out.println("Main Menu:");
                    System.out.println();
                    System.out.println("     1) Create a reimbursement request.");
                    System.out.println("     2) View your pending requests.");
                    System.out.println("     3) View your requests by type.");
                    System.out.println("     4) Update your information.");
                    System.out.println("     5) Change your password.");
                    System.out.println();
                    System.out.println("Press 6 to logout");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    choice = sc.nextInt();
                    while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
                        System.out.println("Invalid option. Menu options are selected by entering the number of the option you wish to select.");
                        System.out.println();
                        System.out.println("     1) Create a reimbursement request.");
                        System.out.println("     2) View your pending requests.");
                        System.out.println("     3) View your requests by type.");
                        System.out.println("     4) Update your address.");
                        System.out.println("     5) Change your password.");
                        System.out.println();
                        System.out.println("Press 6 to logout");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        choice = sc.nextInt();
                    }
                    switch (choice) {
                        case 1:
                            rs.createRequest(loggedInEmployee);
                            break;
                        case 2:
                            rs.viewMyOpenRequests(loggedInEmployee);
                            break;
                        case 3:
                            rs.viewMyRequestsByType(loggedInEmployee);
                            break;
                        case 4:
                            es.updateEmployeeAddress(loggedInEmployee);
                            break;
                        case 5:
                            es.changePassword(loggedInEmployee);
                            break;
                        case 6:
                            System.out.println("Have a good day, " + loggedInEmployee.getFirst() + "!");
                            loggedInEmployee = null;
                            break;
                    }
                }
            }
        }
    }
}
