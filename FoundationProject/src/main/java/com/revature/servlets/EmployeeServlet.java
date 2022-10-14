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
import java.io.IOException;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    EmployeeServiceAPI esa = new EmployeeServiceAPI();
    ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee(1, "Jane", "Doe", "123 cali st", "apt 1", "Huntington Beach", "California", 98210, "jdoe24", "password", EmployeeType.Associate);
        String respPayload = om.writeValueAsString(employee);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("login")){
            Employee employee = om.readValue(req.getInputStream(), Employee.class); /*model.class*/
            Employee emp = esa.login(employee.getUsername(), employee.getPassword());
            String respPayload = om.writeValueAsString(emp);
            resp.getWriter().write(respPayload);
        } else if (req.getParameter("action").equals("register")) {
            Employee employee = om.readValue(req.getInputStream(), Employee.class);
            Employee emp = esa.register(employee.getFirst(), employee.getLast(), employee.getAddress1(), employee.getAddress2(), employee.getCity(), employee.getState(), employee.getZip(), employee.getUsername(), employee.getPassword());
            String respPayload = om.writeValueAsString(emp);
            resp.getWriter().write(respPayload);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
