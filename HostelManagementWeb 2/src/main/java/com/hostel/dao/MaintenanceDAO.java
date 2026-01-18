package com.hostel.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hostel.db.DBConnection;              // ✅ use your com.hostel.db.DBConnection
import com.hostel.model.MaintenanceRequest;     // ✅ use MaintenanceRequest model

public class MaintenanceDAO {

    // STUDENT: Add complaint
    public boolean addRequest(int studentId, String roomNo, String category, String description) {

        String sql = "INSERT INTO maintenance_requests (studentId, roomNo, category, description, status) " +
                     "VALUES (?, ?, ?, ?, 'PENDING')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, roomNo);
            ps.setString(3, category);
            ps.setString(4, description);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // STUDENT: View own complaints
    public List<MaintenanceRequest> getByStudentId(int studentId) {

        List<MaintenanceRequest> list = new ArrayList<>();
        String sql =
        	    "SELECT mr.*, s.username AS studentUsername " +
        	    "FROM maintenance_requests mr " +
        	    "LEFT JOIN students s ON s.id = mr.studentId " +
        	    "WHERE mr.studentId=? " +
        	    "ORDER BY mr.createdAt DESC";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ADMIN: View all complaints
    public List<MaintenanceRequest> getAllRequests() {

        List<MaintenanceRequest> list = new ArrayList<>();
        String sql =
        	    "SELECT mr.*, s.username AS studentUsername " +
        	    "FROM maintenance_requests mr " +
        	    "LEFT JOIN students s ON s.id = mr.studentId " +
        	    "ORDER BY mr.createdAt DESC";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ADMIN: Assign staff and set status to ASSIGNED
    public boolean assignToStaff(int requestId, int staffId) {

        String sql = "UPDATE maintenance_requests SET assignedStaffId=?, status='ASSIGNED' WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, staffId);
            ps.setInt(2, requestId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // STAFF: View tasks
    public List<MaintenanceRequest> getByStaffId(int staffId) {

        List<MaintenanceRequest> list = new ArrayList<>();
        String sql =
        	    "SELECT mr.*, s.username AS studentUsername " +
        	    "FROM maintenance_requests mr " +
        	    "LEFT JOIN students s ON s.id = mr.studentId " +
        	    "WHERE mr.assignedStaffId=? " +
        	    "ORDER BY mr.createdAt DESC";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, staffId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // STAFF: Update status + note
    public boolean updateStatus(int requestId, String status, String note) {

        String sql = "UPDATE maintenance_requests SET status=?, staffNote=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, note);
            ps.setInt(3, requestId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // helper to map DB row -> object
    private MaintenanceRequest map(ResultSet rs) throws SQLException {

        MaintenanceRequest m = new MaintenanceRequest();

        m.setId(rs.getInt("id"));
        m.setStudentId(rs.getInt("studentId"));
        m.setStudentUsername(rs.getString("studentUsername"));
        m.setRoomNo(rs.getString("roomNo"));
        m.setCategory(rs.getString("category"));
        m.setDescription(rs.getString("description"));
        m.setStatus(rs.getString("status"));
        


        int staffVal = rs.getInt("assignedStaffId");
        if (rs.wasNull()) m.setAssignedStaffId(null);
        else m.setAssignedStaffId(staffVal);

        m.setStaffNote(rs.getString("staffNote"));
        m.setCreatedAt(rs.getTimestamp("createdAt"));

        return m;
    }
}
