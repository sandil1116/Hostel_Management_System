package com.hostel.dao;

import com.hostel.db.DBConnection;
import com.hostel.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // ✅ Get available rooms (shows occupancy)
    public List<Room> getAvailableRooms() {
        List<Room> list = new ArrayList<>();

        String sql =
            "SELECT r.room_no, r.capacity, COUNT(s.id) AS occupied " +
            "FROM rooms r " +
            "LEFT JOIN students s ON s.roomNo = r.room_no " +
            "GROUP BY r.room_no, r.capacity " +
            "HAVING occupied < r.capacity " +
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

    // ✅ Assign room to student SAFELY (prevents 2 students taking last bed)
    public boolean assignRoomToStudentSafe(int studentId, String roomNo) {

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // start transaction

            // 1) Lock room row (FOR UPDATE)
            String lockRoomSql = "SELECT capacity FROM rooms WHERE room_no=? FOR UPDATE";
            int capacity;

            try (PreparedStatement ps = con.prepareStatement(lockRoomSql)) {
                ps.setString(1, roomNo);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return false; // room not found
                    }
                    capacity = rs.getInt("capacity");
                }
            }

            // 2) Count current occupancy (inside same transaction)
            String countSql = "SELECT COUNT(*) FROM students WHERE roomNo=?";
            int occupied;

            try (PreparedStatement ps = con.prepareStatement(countSql)) {
                ps.setString(1, roomNo);

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    occupied = rs.getInt(1);
                }
            }

            // If full, reject
            if (occupied >= capacity) {
                con.rollback();
                return false;
            }

            // 3) Assign room to student
            String updateSql = "UPDATE students SET roomNo=? WHERE id=?";
            int updated;

            try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                ps.setString(1, roomNo);
                ps.setInt(2, studentId);
                updated = ps.executeUpdate();
            }

            if (updated != 1) {
                con.rollback();
                return false;
            }

            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ignored) {}
            return false;

        } finally {
            try { if (con != null) con.setAutoCommit(true); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }
 // ✅ Admin adds a room
    public boolean addRoomSimple(String roomNo, int capacity) {
        String sql = "INSERT INTO rooms(room_no, capacity) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, roomNo);
            ps.setInt(2, capacity);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Admin updates capacity
    public boolean updateRoomCapacity(String roomNo, int capacity) {
        String sql = "UPDATE rooms SET capacity=? WHERE room_no=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, capacity);
            ps.setString(2, roomNo);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Admin deletes room
    public boolean deleteRoomSimple(String roomNo) {
        String sql = "DELETE FROM rooms WHERE room_no=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, roomNo);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
