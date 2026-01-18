package com.hostel.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hostel.dao.StaffDAO;
import com.hostel.model.Staff;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {

    private StaffDAO dao = new StaffDAO();

    // READ (show page)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("staffList", dao.getAllStaff());
        request.getRequestDispatcher("staff.jsp").forward(request, response);
    }

    // CREATE / UPDATE / DELETE
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String dept = request.getParameter("department");

            dao.addStaff(new Staff(id, username, password, dept));
        }

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String dept = request.getParameter("department");

            dao.updateStaff(new Staff(id, username, password, dept));
        }

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteStaff(id);
        }

        response.sendRedirect("staff");
    }
}
