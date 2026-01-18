package com.hostel.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hostel.db.DBConnection;
import com.hostel.model.NotificationLog;

public class NotificationDAO {

    // 🔔 Log a simulated email / system notification
    public boolean log(String eventType, String message) {

        String sql = "INSERT INTO notifications_log (event_type, message) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, eventType);
            ps.setString(2, message);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 📜 Admin views all notification logs
    public List<NotificationLog> getAll() {

        List<NotificationLog> list = new ArrayList<>();
        String sql = "SELECT id, event_type, message, createdAt FROM notifications_log " +
                     "ORDER BY createdAt DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NotificationLog n = new NotificationLog();
                n.setId(rs.getInt("id"));
                n.setEventType(rs.getString("event_type"));
                n.setMessage(rs.getString("message"));
                n.setCreatedAt(rs.getTimestamp("createdAt"));
                list.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
