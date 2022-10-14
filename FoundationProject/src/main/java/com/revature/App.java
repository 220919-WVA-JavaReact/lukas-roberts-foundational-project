package com.revature;

import com.revature.menus.Menus;
import com.revature.models.Employee;
import com.revature.models.EmployeeType;
import com.revature.service.EmployeeServiceCLI;
import com.revature.service.RequestServiceCLI;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EmployeeServiceCLI es = new EmployeeServiceCLI();
        RequestServiceCLI rs = new RequestServiceCLI();
        Scanner sc = new Scanner(System.in);
        int id;
        boolean running = true;
        System.out.println("Welcome To Reimburse+");
        while (running) {
            System.out.println("Welcome! Please sign up or login by following the prompts below.");
            Menus.loginRegisterPrompt();
            int choice = sc.nextInt();
            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid option. Menu options are selected by entering the number of the option you wish to select.");
                Menus.loginRegisterPrompt();
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
                if (loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Director)) {
                    Menus.currentUser(loggedInEmployee);
                    System.out.println("Main Menu:");
                    Menus.directorMainMenuPrompt();
                    choice = sc.nextInt();
                    while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 && choice != 8 && choice != 9) {
                        System.out.println("Invalid Option.");
                        Menus.directorMainMenuPrompt();
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
                            es.changeEmployeeLevel(loggedInEmployee);
                            break;
                        case 7:
                            es.updateEmployeeAddress(loggedInEmployee);
                            break;
                        case 8:
                            es.changePassword(loggedInEmployee);
                            break;
                        case 9:
                            System.out.println("Have a good day, " + loggedInEmployee.getFirst() + "!");
                            loggedInEmployee = null;
                            break;
                    }
                } else if (loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Manager)) {
                    Menus.currentUser(loggedInEmployee);
                    System.out.println("Main Menu:");
                    Menus.managerMainMenuPrompt();
                    choice = sc.nextInt();
                    while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 && choice != 8 && choice != 9) {
                        System.out.println("Invalid Option.");
                        Menus.managerMainMenuPrompt();
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
                            es.changeEmployeeLevel(loggedInEmployee);
                            break;
                        case 7:
                            es.updateEmployeeAddress(loggedInEmployee);
                            break;
                        case 8:
                            es.changePassword(loggedInEmployee);
                            break;
                        case 9:
                            System.out.println("Have a good day, " + loggedInEmployee.getFirst() + "!");
                            loggedInEmployee = null;
                            break;
                    }
                } else {
                    Menus.currentUser(loggedInEmployee);
                    System.out.println("Main Menu:");
                    Menus.associateMainMenuPrompt();
                    choice = sc.nextInt();
                    while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
                        System.out.println("Invalid option. Menu options are selected by entering the number of the option you wish to select.");
                        Menus.associateMainMenuPrompt();
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
                sc.nextLine();
            }
        }
    }
}
