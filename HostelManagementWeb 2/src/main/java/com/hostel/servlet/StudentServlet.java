package com.hostel.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hostel.dao.StudentDAO;
import com.hostel.model.Student;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("students", dao.getAllStudents());
        request.getRequestDispatcher("students.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String roomNo = request.getParameter("roomNo");

            Student s = new Student();
            s.setId(id);
            s.setUsername(username);
            s.setPassword(password);
            s.setRoomNo(roomNo);
            s.setAccountStatus("INACTIVE"); // ✅ default until payment verified

            dao.addStudent(s);
        }

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String roomNo = request.getParameter("roomNo");

            Student s = new Student();
            s.setId(id);
            s.setUsername(username);
            s.setPassword(password);
            s.setRoomNo(roomNo);

            // keep old status if you want (optional)
            Student existing = dao.getById(id);
            if (existing != null && existing.getAccountStatus() != null) {
                s.setAccountStatus(existing.getAccountStatus());
            } else {
                s.setAccountStatus("INACTIVE");
            }

            dao.updateStudent(s);
        }

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteStudent(id);
        }

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
