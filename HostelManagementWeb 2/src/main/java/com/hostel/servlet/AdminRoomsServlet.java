package com.hostel.servlet;

import com.hostel.dao.RoomDAO;
import com.hostel.model.Room;
import com.hostel.db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/rooms")
public class AdminRoomsServlet extends HttpServlet {

    private final RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // show rooms with occupancy
        req.setAttribute("rooms", getAllRoomsWithOccupancySimple());
        req.getRequestDispatcher("/adminRooms.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            String roomNo = req.getParameter("roomNo").trim();
            int capacity = parseInt(req.getParameter("capacity"));
            roomDAO.addRoomSimple(roomNo, capacity);

        } else if ("update".equals(action)) {
            String roomNo = req.getParameter("roomNo").trim();
            int capacity = parseInt(req.getParameter("capacity"));
            roomDAO.updateRoomCapacity(roomNo, capacity);

        } else if ("delete".equals(action)) {
            String roomNo = req.getParameter("roomNo").trim();
            roomDAO.deleteRoomSimple(roomNo);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/rooms");
    }

    private int parseInt(String x) {
        try { return Integer.parseInt(x); } catch (Exception e) { return 0; }
    }

    // ✅ Admin view: room_no, capacity, occupied
    private List<Room> getAllRoomsWithOccupancySimple() {
        List<Room> list = new ArrayList<>();

        String sql =
            "SELECT r.room_no, r.capacity, COUNT(s.id) AS occupied " +
            "FROM rooms r " +
            "LEFT JOIN students s ON s.roomNo = r.room_no " +
            "GROUP BY r.room_no, r.capacity " +
            "ORDER BY r.room_no";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room r = new Room();
                r.setRoomNo(rs.getString("room_no"));
                r.setCapacity(rs.getInt("capacity"));
                r.setOccupied(rs.getInt("occupied"));
                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
