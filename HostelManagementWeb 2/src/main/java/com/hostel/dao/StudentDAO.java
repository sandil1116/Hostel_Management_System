package com.hostel.dao;

import com.hostel.db.DBConnection;
import com.hostel.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE (Admin CRUD)
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (id, username, password, roomNo, account_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getUsername());
            ps.setString(3, s.getPassword());
            ps.setString(4, s.getRoomNo());
            ps.setString(5, s.getAccountStatus() == null ? "INACTIVE" : s.getAccountStatus());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Create minimal student record after signup
    public boolean createStudentFromUser(int userId, String username, String password) {
        String sql = "INSERT INTO students (id, username, password, roomNo, account_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, "UNASSIGNED");
            ps.setString(5, "INACTIVE");

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, username, password, roomNo, account_status FROM students ORDER BY id ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setRoomNo(rs.getString("roomNo"));
                s.setAccountStatus(rs.getString("account_status"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student getById(int id) {
        String sql = "SELECT id, username, password, roomNo, account_status FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("id"));
                    s.setUsername(rs.getString("username"));
                    s.setPassword(rs.getString("password"));
                    s.setRoomNo(rs.getString("roomNo"));
                    s.setAccountStatus(rs.getString("account_status"));
                    return s;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getByUsername(String username) {
        String sql = "SELECT id, username, password, roomNo, account_status FROM students WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("id"));
                    s.setUsername(rs.getString("username"));
                    s.setPassword(rs.getString("password"));
                    s.setRoomNo(rs.getString("roomNo"));
                    s.setAccountStatus(rs.getString("account_status"));
                    return s;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET username=?, password=?, roomNo=?, account_status=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getRoomNo());
            ps.setString(4, s.getAccountStatus() == null ? "INACTIVE" : s.getAccountStatus());
            ps.setInt(5, s.getId());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // When admin verifies payment -> ACTIVE
    public boolean setAccountStatus(int studentId, String status) {
        String sql = "UPDATE students SET account_status=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, studentId);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";

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
