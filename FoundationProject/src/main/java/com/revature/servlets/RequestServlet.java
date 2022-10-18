package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.service.RequestServiceAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/tickets")
public class RequestServlet extends HttpServlet {
    RequestServiceAPI rsa = new RequestServiceAPI();
    ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (req.getParameter("action").equals("get-my-tickets")) {
                List<Request> requests = rsa.getMyTickets(lo)
            } else if (req.getParameter("action").equals("get-all-pending")) {

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            Request request = om.readValue(req.getInputStream(), Request.class);
            if (request.getPrice() <= 0 || String.valueOf(request.getPrice()).equals("")) {
                resp.getWriter().write("Price cannot be blank and must be greater than 0.");
                return;
            } else if (request.getDescription().equals("")) {
                resp.getWriter().write("Description cannot be blank.");
                return;
            } else if (request.getType().equals("")) {
                resp.getWriter().write("Type cannot be blank.");
                return;
            } else if (!request.getType().equals("Travel") && !request.getType().equals("Lodging") && !request.getType().equals("Food") && !request.getType().equals("Other")) {
                resp.getWriter().write("Type must only be 'Travel', 'Lodging', 'Food' or 'Other");
                return;
            }
            Request payload = rsa.createRequest(loggedInEmployee, request.getPrice(), request.getDescription(), request.getType());
            resp.getWriter().write(om.writeValueAsString(payload));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (req.getParameter("action").equals("approve-tickets")) {

            } else if (req.getParameter("action").equals("deny-pending")) {

            }
        }
    }
}
