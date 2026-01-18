package com.hostel.dao;

import com.hostel.db.DBConnection;
import com.hostel.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // Student submits payment
    public boolean addPayment(int studentId,
                              double amount,
                              String method,
                              String referenceNo,
                              Date paymentDate,
                              String proofPath) {

        String sql = "INSERT INTO payments " +
                "(student_id, amount, payment_method, reference_no, payment_date, proof_path, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'PENDING')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDouble(2, amount);
            ps.setString(3, method);
            ps.setString(4, referenceNo);
            ps.setDate(5, paymentDate);      // ✅ java.sql.Date
            ps.setString(6, proofPath);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Student: view own payments
    public List<Payment> getPaymentsByStudent(int studentId) {
        List<Payment> list = new ArrayList<>();

        String sql = "SELECT * FROM payments WHERE student_id=? ORDER BY created_at DESC";

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

    // Admin: view all payments
    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();

        String sql =
                "SELECT p.*, s.username AS studentUsername " +
                "FROM payments p " +
                "LEFT JOIN students s ON s.id = p.student_id " +
                "ORDER BY p.created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Payment p = map(rs);
                p.setStudentUsername(rs.getString("studentUsername"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Admin: verify / reject
    public boolean updatePaymentStatus(int paymentId, String status, int adminId) {

        String sql = "UPDATE payments SET status=?, verified_by=?, verified_at=NOW() WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, adminId);
            ps.setInt(3, paymentId);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Payment map(ResultSet rs) throws SQLException {
        Payment p = new Payment();

        p.setId(rs.getInt("id"));
        p.setStudentId(rs.getInt("student_id"));
        p.setAmount(rs.getDouble("amount"));
        p.setPaymentMethod(rs.getString("payment_method"));
        p.setReferenceNo(rs.getString("reference_no"));
        p.setPaymentDate(rs.getDate("payment_date"));
        p.setProofPath(rs.getString("proof_path"));
        p.setStatus(rs.getString("status"));
        p.setCreatedAt(rs.getTimestamp("created_at"));

        int vb = rs.getInt("verified_by");
        p.setVerifiedBy(rs.wasNull() ? null : vb);

        p.setVerifiedAt(rs.getTimestamp("verified_at"));

        return p;
    }
}
