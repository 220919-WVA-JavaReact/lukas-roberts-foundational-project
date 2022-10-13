package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.EmployeeType;
import com.revature.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
//    private final ObjectMapper om;
    EmployeeService es = new EmployeeService();
    ObjectMapper om = new ObjectMapper();

//    public EmployeeServlet(ObjectMapper mapper) {
//        this.om = mapper;
//    }
//
//
//    @Override
//    public void init() throws ServletException {
//        System.out.println("[LOG] - UserServlet Instantiated!");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("[LOG] - UserServlet Destroyed!");
//    }

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
        Employee employee = om.readValue(req.getInputStream(), Employee.class); /*model.class*/
        Employee emp = es.login(employee.getUsername(), employee.getPassword());
        String respPayload = om.writeValueAsString(emp);
        resp.getWriter().write(respPayload);
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
