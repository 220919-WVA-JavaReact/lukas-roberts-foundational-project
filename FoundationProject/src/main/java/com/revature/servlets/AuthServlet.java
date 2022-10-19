package com.revature.servlets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.service.EmployeeServiceAPI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    EmployeeServiceAPI esa = new EmployeeServiceAPI();
    ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session;
        if (req.getParameter("action").equals("login")){
            Employee employee = om.readValue(req.getInputStream(), Employee.class); /*model.class*/    //  <-- json object in request
            Employee emp = esa.login(employee.getUsername(), employee.getPassword());
            String respPayload = om.writeValueAsString(emp);
            if (!respPayload.equals("null")) {
                resp.getWriter().write(respPayload);
                session = req.getSession();
                session.setAttribute("auth-user", emp);
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Invalid credentials");
            }
        } else if (req.getParameter("action").equals("register")) {
            Employee employee = om.readValue(req.getInputStream(), Employee.class);
            Employee emp = esa.register(employee.getFirst(), employee.getLast(), employee.getAddress1(), employee.getAddress2(), employee.getCity(), employee.getState(), employee.getZip(), employee.getUsername(), employee.getPassword());
            String respPayload = om.writeValueAsString(emp);
            if (!respPayload.equals("null")) {
                session = req.getSession();
                session.setAttribute("auth-user", emp);
                resp.setStatus(200);
                resp.getWriter().write(respPayload);
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Username already exists.");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            resp.getWriter().write("You have be logged out successfully.");
        }
    }
}

/* for doPost
        HashMap<String, Object> credentials = om.readValue(req.getInputStream(), HashMap.class);
        String providedUsername = (String) credentials.get("username");
        for (AppUser user : users) {
            if (providedusername.equals(user.getUsername()) && providedPassword.equals(user.getPassword())) {
                sout("Found user");
                HttpSession session = req.getSession();
                session.setAttribute("auth-user", user);
                resp.setStatus(204);
                return;
            }
        }
        resp.setStatus(400);
        resp.setContentType("application/json");
        HashMap<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("Status code", 400);
        errorMessage.put("Message", "No user found with provided credentials");
        errorMessage.put("Timestamp", LocalDateTime.now().toString());
        resp.getWriter().write(mapper.writeValueAsString(errorMessage))

        in other servlet posts
        HttpSession session = req.getSession(false);
        if (session == null {
            resp.setStatus(400);
            resp.setContentType("application/json");
            HashMap<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("Status code", 400);
            errorMessage.put("Message", "No user found with provided credentials");
            errorMessage.put("Timestamp", LocalDateTime.now().toString());
            resp.getWriter().write(mapper.writeValueAsString(errorMessage))
            return;
        }

    to logout
    doDelete{
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        resp.setStatus(204);
     */