
<%@ page import="java.util.*, com.hostel.model.LeaveRequest" %>
<%
    String ctx = request.getContextPath();
    List<LeaveRequest> leaves = (List<LeaveRequest>) request.getAttribute("leaves");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Admin - Leave Requests</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/style.css">
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <h1>Admin/Warden - Leave Requests</h1>
        <p>Approve or reject student leave requests</p>

        <div style="margin-top:10px; display:flex; gap:10px; flex-wrap:wrap;">
          <a class="btn btn-primary" href="<%=ctx%>/admin/complaints">Complaints</a>
          <a class="btn btn-primary" href="<%=ctx%>/admin/rooms">Rooms</a>
          <a class="btn btn-primary" href="<%=ctx%>/admin/leaves">Leaves</a>
        </div>
      </div>

      <a class="btn btn-danger" href="<%=ctx%>/logout">Logout</a>
    </div>

    <div class="card">
      <h2>Leave Requests</h2>

      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Student</th>
            <th>From</th>
            <th>To</th>
            <th>Destination</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Action</th>
          </tr>

          <%
            if (leaves != null && !leaves.isEmpty()) {
              for (LeaveRequest lr : leaves) {
                String st = lr.getStatus();
                boolean pending = "PENDING".equalsIgnoreCase(st);
          %>
          <tr>
            <td><%= lr.getId() %></td>
            <td><%= (lr.getStudentUsername()==null ? lr.getStudentId() : lr.getStudentUsername()) %></td>
            <td><%= lr.getFromDt() %></td>
            <td><%= lr.getToDt() %></td>
            <td><%= lr.getDestination() %></td>
            <td><%= lr.getReason() %></td>
            <td><%= lr.getStatus() %></td>

            <td>
              <% if (pending) { %>
              <form method="post" action="<%=ctx%>/admin/leaves" style="display:flex; gap:8px; align-items:center;">
                <input type="hidden" name="leaveId" value="<%= lr.getId() %>">
                <select name="status" required>
                  <option value="APPROVED">APPROVE</option>
                  <option value="REJECTED">REJECT</option>
                </select>
                <button class="btn btn-primary" type="submit">Submit</button>
              </form>
              <% } else { %>
                -
              <% } %>
            </td>
          </tr>
          <% } } else { %>
          <tr><td colspan="8">No leave requests found.</td></tr>
          <% } %>

        </table>
      </div>
    </div>

  </div>
</div>
</body>
</html>
