package com.hostel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hostel.dao.MaintenanceDAO;
import com.hostel.dao.StaffDAO;
import com.hostel.model.Role;
import com.hostel.model.Staff;
import com.hostel.model.User;

@WebServlet("/staff/tasks")
public class StaffTasksServlet extends HttpServlet {

    private final MaintenanceDAO mdao = new MaintenanceDAO();
    private final StaffDAO sdao = new StaffDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("user");
        if (u == null || u.getRole() != Role.STAFF) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Staff staff = sdao.getByUsername(u.getUsername());
        if (staff == null) {
            response.getWriter().println("Staff record not found in staff table. Add staff in Staff CRUD first.");
            return;
        }

        request.setAttribute("tasks", mdao.getByStaffId(staff.getId()));
        request.getRequestDispatcher("/staffTasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        User u = (User) request.getSession().getAttribute("user");
        if (u == null || u.getRole() != Role.STAFF) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String status = request.getParameter("status");
        String note = request.getParameter("staffNote");

        mdao.updateStatus(requestId, status, note);

        response.sendRedirect(request.getContextPath() + "/staff/tasks");
    }
}
