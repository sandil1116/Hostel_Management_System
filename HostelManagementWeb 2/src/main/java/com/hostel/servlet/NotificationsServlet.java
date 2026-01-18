package com.hostel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hostel.dao.NotificationDAO;
import com.hostel.model.Role;
import com.hostel.model.User;

@WebServlet("/admin/notifications")
public class NotificationsServlet extends HttpServlet {

    private final NotificationDAO ndao = new NotificationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("user");
        if (u == null || u.getRole() != Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        request.setAttribute("logs", ndao.getAll());
        request.getRequestDispatcher("/notifications.jsp").forward(request, response);
    }
}
