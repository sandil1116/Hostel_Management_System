package com.hostel.servlet;

import com.hostel.dao.LeaveDAO;
import com.hostel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/student/leave")
public class StudentLeaveServlet extends HttpServlet {

    private final LeaveDAO leaveDAO = new LeaveDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("leaves", leaveDAO.getLeavesByStudent(user.getId()));
        req.getRequestDispatcher("/studentLeave.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");

        Timestamp fromDt = Timestamp.valueOf(req.getParameter("fromDt"));
        Timestamp toDt = Timestamp.valueOf(req.getParameter("toDt"));
        String reason = req.getParameter("reason");
        String destination = req.getParameter("destination");

        if (toDt.before(fromDt)) {
            req.setAttribute("error", "To Date/Time must be after From Date/Time");
            req.setAttribute("leaves", leaveDAO.getLeavesByStudent(user.getId()));
            try {
                req.getRequestDispatcher("/studentLeave.jsp").forward(req, resp);
            } catch (Exception ignored) {}
            return;
        }

        leaveDAO.addLeave(user.getId(), fromDt, toDt, reason, destination);
        resp.sendRedirect(req.getContextPath() + "/student/leave");
    }
}
