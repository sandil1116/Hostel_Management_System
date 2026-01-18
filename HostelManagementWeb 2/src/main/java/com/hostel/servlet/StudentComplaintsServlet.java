package com.hostel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hostel.dao.MaintenanceDAO;
import com.hostel.dao.StudentDAO;
import com.hostel.model.Role;
import com.hostel.model.Student;
import com.hostel.model.User;

@WebServlet("/student/complaints")
public class StudentComplaintsServlet extends HttpServlet {

    private final MaintenanceDAO mdao = new MaintenanceDAO();
    private final StudentDAO sdao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("user");
        if (u == null || u.getRole() != Role.STUDENT) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Student student = sdao.getByUsername(u.getUsername());
        if (student == null) {
            response.getWriter().println("Student record not found in students table.");
            return;
        }

        // ✅ correct DAO method
        request.setAttribute("list", mdao.getByStudentId(student.getId()));
        request.getRequestDispatcher("/studentComplaints.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        User u = (User) request.getSession().getAttribute("user");
        if (u == null || u.getRole() != Role.STUDENT) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Student student = sdao.getByUsername(u.getUsername());
        if (student == null) {
            response.sendRedirect(request.getContextPath() + "/student/complaints?error=nostudent");
            return;
        }

        String roomNo = request.getParameter("roomNo");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        // ✅ correct DAO method
        mdao.addRequest(student.getId(), roomNo, category, description);

        response.sendRedirect(request.getContextPath() + "/student/complaints");
    }
}
