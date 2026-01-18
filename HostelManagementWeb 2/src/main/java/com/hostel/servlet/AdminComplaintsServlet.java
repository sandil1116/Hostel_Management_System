package com.hostel.servlet;

import com.hostel.dao.NotificationDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hostel.dao.MaintenanceDAO;
import com.hostel.dao.StaffDAO;
import com.hostel.model.Role;
import com.hostel.model.User;

@WebServlet("/admin/complaints")
public class AdminComplaintsServlet extends HttpServlet {

    private final MaintenanceDAO mdao = new MaintenanceDAO();
    private final StaffDAO sdao = new StaffDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("user");
        if (u == null || u.getRole() != Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        //  correct DAO method
        request.setAttribute("requests", mdao.getAllRequests());
        request.setAttribute("staffList", sdao.getAllStaff());

        request.getRequestDispatcher("/adminComplaints.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        User u = (User) request.getSession().getAttribute("user");
        if (u == null || u.getRole() != Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        int staffId = Integer.parseInt(request.getParameter("staffId"));

        // ✅ correct DAO method
        mdao.assignToStaff(requestId, staffId);

        response.sendRedirect(request.getContextPath() + "/admin/complaints");
    }
}
