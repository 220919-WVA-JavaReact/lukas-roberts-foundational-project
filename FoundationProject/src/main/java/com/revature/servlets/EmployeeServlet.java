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
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    EmployeeServiceAPI esa = new EmployeeServiceAPI();
    ObjectMapper om = new ObjectMapper();
    List<Employee> employees;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Director)) {
                employees = esa.getAllEmployees(loggedInEmployee);
                String respPayload = om.writeValueAsString(employees);
                resp.getWriter().write(respPayload);
                resp.setStatus(200);
            } else if(loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Manager)) {
                employees = esa.getEmployeeByLevel(EmployeeType.Associate);
                String respPayload = om.writeValueAsString(employees);
                resp.getWriter().write(respPayload);
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Associates are not allowed to view all the other employees.");
            }
        } else {
            resp.getWriter().write("You must be logged in to view this page.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Director)) {
                employees = esa.getAllEmployees(loggedInEmployee);
                String respPayload = om.writeValueAsString(employees);
                resp.getWriter().write(respPayload);
                resp.setStatus(200);
            } else if(loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Manager)) {
                employees = esa.getEmployeeByLevel(EmployeeType.Associate);
                String respPayload = om.writeValueAsString(employees);
                resp.getWriter().write(respPayload);
                resp.setStatus(200);
            } else {
                resp.getWriter().write("Associates are not allowed to promote or demote employees.");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
