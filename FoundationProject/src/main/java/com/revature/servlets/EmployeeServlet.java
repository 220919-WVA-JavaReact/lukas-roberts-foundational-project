package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.EmployeeType;
import com.revature.service.EmployeeServiceAPI;
import com.revature.service.EmployeeServiceCLI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    EmployeeServiceAPI esa = new EmployeeServiceAPI();
    ObjectMapper om = new ObjectMapper();
    List<Employee> employees;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Director)) {
                employees = esa.getAllEmployees(loggedInEmployee);
                if (employees != null) {
                    String respPayload = om.writeValueAsString(employees);
                    resp.getWriter().write(respPayload);
                    resp.setStatus(200);
                } else {
                    resp.getWriter().write("There are no employees under your level.");
                }

            } else if(loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Manager)) {
                employees = esa.getEmployeeByLevel(EmployeeType.Associate);
                if (employees != null) {
                    String respPayload = om.writeValueAsString(employees);
                    resp.getWriter().write(respPayload);
                    resp.setStatus(200);
                } else {
                    resp.getWriter().write("There are no employees under your level.");
                }

            } else {
                resp.setStatus(400);
                resp.getWriter().write("Associates are not allowed to view all the other employees.");
            }
        } else {
            resp.getWriter().write("You must be logged in to view this page.");
        }
    }


    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //                                          use service to check for session
    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (req.getParameter("action").equals("promote")) {
                HashMap<String, Object> jsonInput = om.readValue(req.getInputStream(), HashMap.class);
                Employee employeeToChange = esa.getEmployee((String) jsonInput.get("username"));
                if (employeeToChange.getEmployeeLevel().equals(EmployeeType.valueOf((String) jsonInput.get("employee-type")))) {
                    resp.getWriter().write("That employee is already the requested level.");
                } else {
                    Employee promotedEmployee = esa.changeEmployeeLevel(employeeToChange.getId(), EmployeeType.valueOf((String) jsonInput.get("employee-type")));
                    resp.getWriter().write(om.writeValueAsString(promotedEmployee));
                }

//--------------------------------------------------------------------------------------------------------------
            } else if (req.getParameter("action").equals("demote")) {
                HashMap<String, Object> jsonInput = om.readValue(req.getInputStream(), HashMap.class);
                Employee employeeToChange = esa.getEmployee((String) jsonInput.get("username"));
                if (employeeToChange.getEmployeeLevel().equals(EmployeeType.valueOf((String) jsonInput.get("employee-type")))) {
                    resp.getWriter().write("That employee is already the requested level.");
                } else {
                    Employee promotedEmployee = esa.changeEmployeeLevel(employeeToChange.getId(), EmployeeType.valueOf((String) jsonInput.get("employee-type")));
                    resp.getWriter().write(om.writeValueAsString(promotedEmployee));
                }

//--------------------------------------------------------------------------------------------------------------
            } else if (req.getParameter("action").equals("change-address")) {
                HashMap<String, Object> jsonInput = om.readValue(req.getInputStream(), HashMap.class);
                Employee updatedEmployee = esa.updateEmployeeAddress(loggedInEmployee.getId(), (String) jsonInput.get("address-1"), (String) jsonInput.get("address-2"), (String) jsonInput.get("city"), (String) jsonInput.get("state"), (int) jsonInput.get("zip"));
                resp.getWriter().write(om.writeValueAsString(updatedEmployee));
//--------------------------------------------------------------------------------------------------------------
            } else if (req.getParameter("action").equals("change-password")) {
                HashMap<String, Object> jsonInput = om.readValue(req.getInputStream(), HashMap.class);
                String providedPassword = (String) jsonInput.get("current-password");
                if (loggedInEmployee.getPassword().equals(providedPassword)) {
                    if (loggedInEmployee.getPassword().equals((String) jsonInput.get("new-password"))) {
                        resp.getWriter().write("Your new password cannot be the same as your current password. Failed to update password.");
                    } else {
                        Employee employee = esa.changePassword(loggedInEmployee, (String) jsonInput.get("new-password"));
                        if (employee != null) {
                            String respPayload = om.writeValueAsString(employee);
                            resp.getWriter().write(respPayload);
                        } else {
                            resp.getWriter().write("Failed to update your password");
                        }
                    }
                } else {
                    resp.getWriter().write("Your current password is incorrect. Failed to update password.");
                }
            }
        } else {
            resp.getWriter().write("You must be logged in to view this page.");
        }
    }
}
