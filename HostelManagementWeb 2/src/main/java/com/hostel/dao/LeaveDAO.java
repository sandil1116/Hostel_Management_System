package com.hostel.dao;

import com.hostel.db.DBConnection;
import com.hostel.model.LeaveRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveDAO {

    // ✅ Student submits leave (INSERT)
    public boolean addLeave(int studentId, Timestamp fromDt, Timestamp toDt, String reason, String destination) {

        String sql = "INSERT INTO leave_requests(student_id, from_dt, to_dt, reason, destination, status) " +
                     "VALUES (?,?,?,?,?,'PENDING')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setTimestamp(2, fromDt);
            ps.setTimestamp(3, toDt);
            ps.setString(4, reason);
            ps.setString(5, destination);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Student view own leaves
    public List<LeaveRequest> getLeavesByStudent(int studentId) {
        List<LeaveRequest> list = new ArrayList<>();

        String sql = "SELECT * FROM leave_requests WHERE student_id=? ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Admin view all leaves (JOIN students for username)
    public List<LeaveRequest> getAllLeaves() {
        List<LeaveRequest> list = new ArrayList<>();

        String sql =
            "SELECT lr.*, s.username AS studentUsername " +
            "FROM leave_requests lr " +
            "LEFT JOIN students s ON s.id = lr.student_id " +   // ✅ change s.id if your PK differs
            "ORDER BY lr.created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LeaveRequest lr = map(rs);
                lr.setStudentUsername(rs.getString("studentUsername"));
                list.add(lr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Admin approve / reject
    public boolean updateStatus(int leaveId, String status, int adminId) {

        String sql = "UPDATE leave_requests SET status=?, approved_by=?, approved_at=NOW() WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, adminId);
            ps.setInt(3, leaveId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private LeaveRequest map(ResultSet rs) throws SQLException {
        LeaveRequest lr = new LeaveRequest();
        lr.setId(rs.getInt("id"));
        lr.setStudentId(rs.getInt("student_id"));
        lr.setFromDt(rs.getTimestamp("from_dt"));
        lr.setToDt(rs.getTimestamp("to_dt"));
        lr.setReason(rs.getString("reason"));
        lr.setDestination(rs.getString("destination"));
        lr.setStatus(rs.getString("status"));
        lr.setCreatedAt(rs.getTimestamp("created_at"));

        int a = rs.getInt("approved_by");
        if (rs.wasNull()) lr.setApprovedBy(null);
        else lr.setApprovedBy(a);

        lr.setApprovedAt(rs.getTimestamp("approved_at"));
        return lr;
    }
}
