package com.revature.menus;
import com.revature.models.Employee;

public class Menus {
    public static void loginRegisterPrompt() {
        System.out.println();
        System.out.println("      To register, press 1.");
        System.out.println("      To log in, press 2.");
        System.out.println();
        System.out.println("Press 3 to quit.");
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public static void currentUser(Employee loggedInEmployee) {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Current User: " + loggedInEmployee.getEmployeeLevel() + " " + loggedInEmployee.getFirst() + " " + loggedInEmployee.getLast());
        System.out.println();
    }

    public static void directorMainMenuPrompt() {
        System.out.println();
        System.out.println("     1) View a specific request(request id required).");
        System.out.println("     2) View all requests.");
        System.out.println("     3) View requests by status.");
        System.out.println("     4) Update a request(request id required).");
        System.out.println("     5) View all requests of a specific employee(employee id required).");
        System.out.println("     6) Promote or demote a manager.");
        System.out.println("     7) Update your address.");
        System.out.println("     8) Change your password.");
        System.out.println();
        System.out.println("Press 9 to logout.");
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public static void managerMainMenuPrompt() {
        System.out.println();
        System.out.println("     1) View a specific request(request id required).");
        System.out.println("     2) View all pending requests.");
        System.out.println("     3) View all requests by status.");
        System.out.println("     4) Update a request(request id required).");
        System.out.println("     5) View all requests of a specific associate(employee id required).");
        System.out.println("     6) Promote an associate.");
        System.out.println("     7) Update your address.");
        System.out.println("     8) Change your password.");
        System.out.println();
        System.out.println("Press 9 to logout.");
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public static void associateMainMenuPrompt() {
        System.out.println();
        System.out.println("     1) Create a reimbursement request.");
        System.out.println("     2) View your pending requests.");
        System.out.println("     3) View your requests by type.");
        System.out.println("     4) Update your address.");
        System.out.println("     5) Change your password.");
        System.out.println();
        System.out.println("Press 6 to logout");
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
}
