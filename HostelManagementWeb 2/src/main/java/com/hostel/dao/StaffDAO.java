package com.hostel.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hostel.db.DBConnection;
import com.hostel.model.Staff;

public class StaffDAO {

    // CREATE
    public boolean addStaff(Staff s) {
        String sql = "INSERT INTO staff (id, username, password, department) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getUsername());
            ps.setString(3, s.getPassword());
            ps.setString(4, s.getDepartment());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ ALL
    public List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT id, username, password, department FROM staff ORDER BY id ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Staff s = new Staff();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setDepartment(rs.getString("department"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // FIND BY ID
    public Staff getById(int id) {
        String sql = "SELECT id, username, password, department FROM staff WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Staff s = new Staff();
                    s.setId(rs.getInt("id"));
                    s.setUsername(rs.getString("username"));
                    s.setPassword(rs.getString("password"));
                    s.setDepartment(rs.getString("department"));
                    return s;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // FIND BY USERNAME
    public Staff getByUsername(String username) {
        String sql = "SELECT id, username, password, department FROM staff WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Staff s = new Staff();
                    s.setId(rs.getInt("id"));
                    s.setUsername(rs.getString("username"));
                    s.setPassword(rs.getString("password"));
                    s.setDepartment(rs.getString("department"));
                    return s;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public boolean updateStaff(Staff s) {
        String sql = "UPDATE staff SET username=?, password=?, department=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getDepartment());
            ps.setInt(4, s.getId());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteStaff(int id) {
        String sql = "DELETE FROM staff WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
