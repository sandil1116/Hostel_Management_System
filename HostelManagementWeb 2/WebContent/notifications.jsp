<%@ page import="java.util.*, com.hostel.model.NotificationLog" %>
<%
  List<NotificationLog> logs = (List<NotificationLog>) request.getAttribute("logs");
  String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
  <title>System Notifications Log</title>
</head>
<body>

<h2>System Notifications Log (Email Simulation)</h2>

<% if (logs == null || logs.isEmpty()) { %>
  <p><b>No notifications yet.</b></p>
<% } else { %>
<table border="1" cellpadding="8">
  <tr>
    <th>ID</th>
    <th>Type</th>
    <th>Message</th>
    <th>Time</th>
  </tr>
  <% for (NotificationLog n : logs) { %>
  <tr>
    <td><%= n.getId() %></td>
    <td><%= n.getEventType() %></td>
    <td><%= n.getMessage() %></td>
    <td><%= n.getCreatedAt() %></td>
  </tr>
  <% } %>
</table>
<% } %>

<br>
<a href="<%= ctx %>/admin/complaints">Back to Admin Complaints</a>

</body>
</html>
<a href="<%= request.getContextPath() %>/logout">Logout</a>
