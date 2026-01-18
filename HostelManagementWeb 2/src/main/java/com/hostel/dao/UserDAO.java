package com.hostel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.hostel.db.DBConnection;
import com.hostel.model.Role;
import com.hostel.model.User;

public class UserDAO {

    // ✅ LOGIN
    public User login(String username, String password, Role role) {
        String sql = "SELECT id, username, password, role FROM users WHERE username=? AND password=? AND role=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role.name());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ check if username exists (role-based)
    public boolean usernameExists(String username, Role role) {
        String sql = "SELECT 1 FROM users WHERE username=? AND role=? LIMIT 1";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, role.name());

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return true; // safer
        }
    }

    // ✅ CREATE USER and RETURN new ID (for RegisterServlet)
    public int createUserReturnId(String username, String password, Role role) {

        if (usernameExists(username, role)) return -1;

        String sql = "INSERT INTO users(username, password, role) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role.name());

            int rows = ps.executeUpdate();
            if (rows == 0) return -1;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
