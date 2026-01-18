package com.hostel.servlet;

import com.hostel.dao.PaymentDAO;
import com.hostel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;

@WebServlet("/student/payment")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1MB
        maxFileSize = 10 * 1024 * 1024,       // 10MB
        maxRequestSize = 20 * 1024 * 1024     // 20MB
)
public class StudentPaymentServlet extends HttpServlet {

    private final PaymentDAO paymentDAO = new PaymentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        req.setAttribute("payments", paymentDAO.getPaymentsByStudent(u.getId()));
        req.getRequestDispatcher("/studentPayment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            double amount = Double.parseDouble(req.getParameter("amount"));
            String method = req.getParameter("method");
            String ref = req.getParameter("ref");
            Date date = Date.valueOf(req.getParameter("date"));

            Part proof = req.getPart("proof");
            String proofPath = null;

            // Save file if uploaded
            if (proof != null && proof.getSize() > 0) {

                // Folder inside your web app
                String uploadsDir = getServletContext().getRealPath("/uploads");
                File dir = new File(uploadsDir);
                if (!dir.exists()) dir.mkdirs();

                String originalName = Paths.get(proof.getSubmittedFileName()).getFileName().toString();
                String safeName = "pay_" + u.getId() + "_" + System.currentTimeMillis() + "_" + originalName;

                String fullPath = uploadsDir + File.separator + safeName;
                proof.write(fullPath);

                proofPath = "uploads/" + safeName; // stored in DB
            }

            boolean ok = paymentDAO.addPayment(u.getId(), amount, method, ref, date, proofPath);

            if (ok) req.setAttribute("msg", "Payment submitted successfully. Waiting for admin verification.");
            else req.setAttribute("error", "Payment insert failed.");

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("error", "Invalid input. Please check and try again.");
        }

        // Reload page
        req.setAttribute("payments", paymentDAO.getPaymentsByStudent(u.getId()));
        req.getRequestDispatcher("/studentPayment.jsp").forward(req, resp);
    }
}
