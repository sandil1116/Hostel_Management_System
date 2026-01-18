package com.hostel.servlet;

import com.hostel.dao.PaymentDAO;
import com.hostel.dao.StudentDAO;
import com.hostel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/payments")
public class AdminPaymentsServlet extends HttpServlet {

    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    // ✅ FIX: Allow GET to open the page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User admin = (User) req.getSession().getAttribute("user");
        if (admin == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        req.setAttribute("payments", paymentDAO.getAllPayments());
        req.getRequestDispatcher("/adminPayments.jsp").forward(req, resp);
    }

    // Admin verifies/rejects (POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        User admin = (User) req.getSession().getAttribute("user");
        if (admin == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int paymentId = Integer.parseInt(req.getParameter("paymentId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String status = req.getParameter("status"); // VERIFIED / REJECTED
        int adminId = admin.getId();

        boolean ok = paymentDAO.updatePaymentStatus(paymentId, status, adminId);

        // ✅ if verified -> activate student
        if (ok && "VERIFIED".equalsIgnoreCase(status)) {
            studentDAO.setAccountStatus(studentId, "ACTIVE");
        }

        resp.sendRedirect(req.getContextPath() + "/admin/payments");
    }
}
