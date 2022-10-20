package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.EmployeeType;
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
import java.util.List;

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
                List<Request> requests = rsa.getMyTickets(loggedInEmployee.getId());
                if (requests.size() < 1) {
                    resp.getWriter().write("You currently have no submitted requests.");
                    resp.setStatus(200);
                } else {
                    String payload = om.writeValueAsString(requests);
                    resp.getWriter().write(payload);
                    resp.setStatus(200);
                }
            } else if (req.getParameter("action").equals("get-tickets-by-status")) {
                if (loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Associate)) {
                    resp.getWriter().write("You must be logged in as a manager to view this information.");
                    resp.setStatus(401);
                } else {
                    if (req.getParameter("status").equals("Pending")) {
                        List<Request> requests = rsa.getTicketsByStatus("Pending");
                        String payload = om.writeValueAsString(requests);
                        resp.getWriter().write(payload);
                        resp.setStatus(200);
                    } else if (req.getParameter("status").equals("Approved")) {
                        List<Request> requests = rsa.getTicketsByStatus("Approved");
                        String payload = om.writeValueAsString(requests);
                        resp.getWriter().write(payload);
                        resp.setStatus(200);
                    } else if (req.getParameter("status").equals("Denied")) {
                        List<Request> requests = rsa.getTicketsByStatus("Denied");
                        String payload = om.writeValueAsString(requests);
                        resp.getWriter().write(payload);
                        resp.setStatus(200);
                    }
                }
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
                resp.setStatus(400);
            } else if (request.getDescription().equals("")) {
                resp.getWriter().write("Description cannot be blank.");
                resp.setStatus(400);
            } else if (request.getType().equals("")) {
                resp.getWriter().write("Type cannot be blank.");
                resp.setStatus(400);
            } else if (!request.getType().equals("Travel") && !request.getType().equals("Lodging") && !request.getType().equals("Food") && !request.getType().equals("Other")) {
                resp.getWriter().write("Type must only be 'Travel', 'Lodging', 'Food' or 'Other");
                resp.setStatus(400);
            } else {
                Request payload = rsa.createRequest(loggedInEmployee, request.getPrice(), request.getDescription(), request.getType());
                resp.getWriter().write(om.writeValueAsString(payload));
                resp.setStatus(201);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-user");
            if (loggedInEmployee.getEmployeeLevel().equals(EmployeeType.Associate)) {
                resp.getWriter().write("Associates are not allowed to approve or deny tickets.");
                resp.setStatus(401);
            } else {
                // can't approve your own tickets and can't update a completed ticket
                // get ticket check completed
                HashMap<String, Object> jsonInput = om.readValue(req.getInputStream(), HashMap.class);
                Request request = rsa.getTicketById((int) jsonInput.get("ticket-id"));
                if (!request.isCompleted()) {
                    if (request.getEmployee().getId() != loggedInEmployee.getId()) {
                        if (req.getParameter("action").equals("approve-ticket")) {
                            Request updatedRequest = rsa.updateRequest(request.getId(), "Approved");
                            resp.getWriter().write(om.writeValueAsString(updatedRequest));
                            resp.setStatus(201);
                        } else if (req.getParameter("action").equals("deny-ticket")) {
                            Request updatedRequest = rsa.updateRequest(request.getId(), "Approved");
                            resp.getWriter().write(om.writeValueAsString(updatedRequest));
                            resp.setStatus(201);
                        }
                    } else {
                        resp.getWriter().write("Managers cannot approve or deny their own tickets.");
                        resp.setStatus(401);
                    }
                } else {
                    resp.getWriter().write("That request has already been completed. Completed requests cannot be altered.");
                    resp.setStatus(400);
                }
            }
        }
    }
}
