<%@ page import="java.util.*, com.hostel.model.LeaveRequest" %>
<%
    String ctx = request.getContextPath();
    List<LeaveRequest> leaves = (List<LeaveRequest>) request.getAttribute("leaves");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Student Leave</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/style.css">
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <h1>Leave Requests</h1>
        <p>Submit and track your leave requests</p>
      </div>
      <a class="btn btn-danger" href="<%=ctx%>/logout">Logout</a>
    </div>

    <div class="card">
      <h2>Submit Leave</h2>

      <% if (error != null) { %>
        <p style="color:red;"><%=error%></p>
      <% } %>

      <form method="post" action="<%=ctx%>/student/leave">
        From (YYYY-MM-DD HH:MM:SS):
        <input name="fromDt" placeholder="2026-01-14 10:00:00" required>

        To (YYYY-MM-DD HH:MM:SS):
        <input name="toDt" placeholder="2026-01-15 18:00:00" required>

        Destination:
        <input name="destination" placeholder="Home / City">

        Reason:
        <input name="reason" required>

        <button class="btn btn-primary" type="submit">Submit</button>
      </form>
    </div>

    <div class="card">
      <h2>My Leave History</h2>
      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th><th>From</th><th>To</th><th>Destination</th><th>Reason</th><th>Status</th>
          </tr>

          <%
            if (leaves != null && !leaves.isEmpty()) {
              for (LeaveRequest lr : leaves) {
          %>
          <tr>
            <td><%=lr.getId()%></td>
            <td><%=lr.getFromDt()%></td>
            <td><%=lr.getToDt()%></td>
            <td><%=lr.getDestination()%></td>
            <td><%=lr.getReason()%></td>
            <td><%=lr.getStatus()%></td>
          </tr>
          <% } } else { %>
          <tr><td colspan="6">No leave requests yet.</td></tr>
          <% } %>
        </table>
      </div>
    </div>

  </div>
</div>
</body>
</html>
