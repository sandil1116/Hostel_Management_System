<%@ page import="java.util.*, com.hostel.model.MaintenanceRequest" %>
<%
    List<MaintenanceRequest> tasks = (List<MaintenanceRequest>) request.getAttribute("tasks");
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="<%= ctx %>/assets/style.css">
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <h1>Maintenance Staff Dashboard</h1>
        <p>Update work progress and notes</p>
      </div>
      <a class="btn btn-danger" href="<%= ctx %>/logout">Logout</a>
    </div>

    <div class="card">
      <h2>Assigned Tasks</h2>

      <% if (tasks == null || tasks.isEmpty()) { %>
        <p><b>No tasks assigned yet.</b></p>
      <% } else { %>

      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Room</th>
            <th>Category</th>
            <th>Issue</th>
            <th>Status</th>
            <th>Update</th>
          </tr>

          <% for (MaintenanceRequest t : tasks) { %>
          <tr>
            <td><%= t.getId() %></td>
            <td><%= t.getRoomNo() %></td>
            <td><%= t.getCategory() %></td>
            <td><%= t.getDescription() %></td>
            <td><%= t.getStatus() %></td>

            <td>
              <form method="post" action="<%= ctx %>/staff/tasks" style="display:flex; gap:8px; align-items:center;">
                <input type="hidden" name="requestId" value="<%= t.getId() %>"/>

                <select name="status" required>
                  <option value="ASSIGNED" <%= "ASSIGNED".equals(t.getStatus()) ? "selected" : "" %>>ASSIGNED</option>
                  <option value="ONGOING"  <%= "ONGOING".equals(t.getStatus()) ? "selected" : "" %>>ONGOING</option>
                  <option value="DONE"     <%= "DONE".equals(t.getStatus()) ? "selected" : "" %>>DONE</option>
                </select>

                <input type="text" name="staffNote" placeholder="Add note"
                  value="<%= (t.getStaffNote()==null ? "" : t.getStaffNote()) %>" />

                <button class="btn btn-dark" type="submit">Save</button>
              </form>
            </td>
          </tr>
          <% } %>

        </table>
      </div>

      <% } %>
    </div>

  </div>
</div>
</body>
</html>
