package com.hostel.servlet;

import com.hostel.dao.StudentDAO;
import com.hostel.dao.UserDAO;
import com.hostel.model.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String roleStr  = req.getParameter("role"); // STUDENT / STAFF

        // basic validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            roleStr == null || roleStr.trim().isEmpty()) {

            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // parse Role enum
        Role role;
        try {
            role = Role.valueOf(roleStr.trim().toUpperCase());
        } catch (Exception e) {
            req.setAttribute("error", "Invalid role selected.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // 1) create user in users table and get new ID
        int newUserId = userDAO.createUserReturnId(username.trim(), password.trim(), role);

        if (newUserId == -1) {
            req.setAttribute("error", "Username already exists or user insert failed.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // 2) if Student -> also create row in students table
        if (role == Role.STUDENT) {
            boolean okStudent = studentDAO.createStudentFromUser(newUserId, username.trim(), password.trim());

            if (!okStudent) {
                req.setAttribute("error", "User created but student profile insert failed.");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }
        }

        // success
        req.setAttribute("msg", "Account created! Please login.");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
