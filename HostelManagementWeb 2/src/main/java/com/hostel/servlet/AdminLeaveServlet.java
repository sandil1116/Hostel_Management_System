package com.hostel.servlet;

import com.hostel.dao.LeaveDAO;
import com.hostel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/leaves")
public class AdminLeaveServlet extends HttpServlet {

    private final LeaveDAO leaveDAO = new LeaveDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("leaves", leaveDAO.getAllLeaves());
        req.getRequestDispatcher("/adminLeaves.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int leaveId = Integer.parseInt(req.getParameter("leaveId"));
        String status = req.getParameter("status"); // APPROVED or REJECTED

        User admin = (User) req.getSession().getAttribute("user");
        int adminId = admin.getId();

        leaveDAO.updateStatus(leaveId, status, adminId);

        resp.sendRedirect(req.getContextPath() + "/admin/leaves");
    }
}
