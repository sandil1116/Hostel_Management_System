package com.hostel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hostel.dao.UserDAO;
import com.hostel.model.Role;
import com.hostel.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String roleStr = request.getParameter("role");

        Role role = Role.valueOf(roleStr); // ADMIN/STUDENT/STAFF

        User user = userDAO.login(username, password, role);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=1");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // redirect based on role
        if (role == Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/admin/complaints");
        } else if (role == Role.STAFF) {
            response.sendRedirect(request.getContextPath() + "/staff/tasks");
        } else {
            response.sendRedirect(request.getContextPath() + "/student/complaints");
        }
    }
}
