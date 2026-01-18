package com.hostel.servlet;

import com.hostel.dao.RoomDAO;
import com.hostel.dao.StudentDAO;
import com.hostel.model.Student;
import com.hostel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/student/choose-room")
public class ChooseRoomServlet extends HttpServlet {

    private final RoomDAO roomDAO = new RoomDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // show current assigned room
        Student s = studentDAO.getById(u.getId());
        req.setAttribute("student", s);

        req.setAttribute("rooms", roomDAO.getAvailableRooms());
        req.getRequestDispatcher("/chooseRoom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String roomNo = req.getParameter("roomNo");
        if (roomNo == null || roomNo.trim().isEmpty()) {
            req.setAttribute("error", "Please select a room.");
            doGet(req, resp);
            return;
        }

        boolean ok = roomDAO.assignRoomToStudentSafe(u.getId(), roomNo.trim());

        if (ok) req.setAttribute("msg", "Room allocated successfully: " + roomNo);
        else req.setAttribute("error", "Room is full / not available. Please select another room.");

        doGet(req, resp);
    }
}
