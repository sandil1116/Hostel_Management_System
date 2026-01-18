package com.hostel.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hostel.db.DBConnection;

@WebServlet("/testdb")
public class TestDBServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Connection con = DBConnection.getConnection();
        res.getWriter().println(con != null ? "DB CONNECTED SUCCESSFULLY ✅" : "DB CONNECTION FAILED ❌");
    }
}
