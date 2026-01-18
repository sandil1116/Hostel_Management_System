<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String ctx = request.getContextPath();
  String error = (String) request.getAttribute("error");
  String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Create Account</title>
  <link rel="stylesheet" href="<%= ctx %>/assets/style.css">
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <h1>Create Account</h1>
        <p>Register as Student / Staff</p>
      </div>
      <a class="btn btn-primary" href="<%= ctx %>/login.jsp">Back to Login</a>
    </div>

    <div class="card">
      <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
      <% } %>
      <% if (msg != null) { %>
        <p style="color:green;"><%= msg %></p>
      <% } %>

      <form method="post" action="<%= ctx %>/register">
        <label>Username</label>
        <input type="text" name="username" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <label>Role</label>
        <select name="role" required>
          <option value="STUDENT">STUDENT</option>
        </select>

        <button class="btn btn-primary" type="submit">Create Account</button>
      </form>
    </div>

  </div>
</div>
</body>
</html>
