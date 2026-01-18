<%@ page import="com.hostel.model.User" %>
<%
    User u = (User) session.getAttribute("user");
    String ctx = request.getContextPath();
%>

<div class="topbar">
  <div class="brand">
    <h1>Hostel Management System</h1>
  </div>

  <div style="display:flex; gap:10px; align-items:center;">
    <% if (u != null) { %>
      <span style="color:white;">Logged in as: <b><%=u.getUsername()%></b></span>
      <a class="btn btn-danger" href="<%=ctx%>/logout">Logout</a>
    <% } %>
  </div>
</div>
