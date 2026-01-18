<jsp:include page="/partials/logout.jsp" />


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.hostel.model.MaintenanceRequest, com.hostel.model.Staff" %>
<%
    List<MaintenanceRequest> requests = (List<MaintenanceRequest>) request.getAttribute("requests");
    List<Staff> staffList = (List<Staff>) request.getAttribute("staffList");
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%= ctx %>/assets/style.css">
    
    

    
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <h1>Admin/Warden Dashboard</h1>
        <p>View complaints & assign maintenance staff</p>

        <!-- ✅ NEW: Simple admin navigation -->
        <div style="margin-top:10px; display:flex; gap:10px; flex-wrap:wrap;">
          <a class="btn btn-primary" href="<%= ctx %>/admin/complaints">Complaints</a>
          <a class="btn btn-primary" href="<%= ctx %>/admin/rooms">Manage Rooms</a>
                      <a class="btn btn-primary" href="<%= ctx %>/admin/leaves">Leave Requests</a>
                      <a class="btn btn-primary" href="<%= ctx %>/admin/payments">Payments</a>
           	
        </div>
      </div>

      <a class="btn btn-danger" href="<%= ctx %>/logout">Logout</a>
    </div>

    <div class="card">
      <h2>Complaints Queue</h2>

      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Student</th>
            <th>Room</th>
            <th>Category</th>
            <th>Description</th>
            <th>Status</th>
            <th>Assigned Staff</th>
            <th>Assign</th>
          </tr>

          <%
            if (requests != null && !requests.isEmpty()) {
              for (MaintenanceRequest r : requests) {

                String st = (r.getStatus() == null || r.getStatus().trim().isEmpty())
                        ? "PENDING" : r.getStatus();

                String badge = "badge-pending";
                if ("ASSIGNED".equalsIgnoreCase(st)) badge="badge-assigned";
                if ("ONGOING".equalsIgnoreCase(st)) badge="badge-ongoing";
                if ("DONE".equalsIgnoreCase(st)) badge="badge-done";

                boolean isDone = "DONE".equalsIgnoreCase(st);
          %>
          <tr>
            <td><%= r.getId() %></td>
            <td><%= r.getStudentUsername() %></td>
            <td><%= r.getRoomNo() %></td>
            <td><%= r.getCategory() %></td>
            <td><%= r.getDescription() %></td>
            <td><span class="badge <%= badge %>"><%= st %></span></td>
            <td><%= (r.getAssignedStaffId()==null ? "-" : r.getAssignedStaffId()) %></td>

            <td>
              <form method="post" action="<%= ctx %>/admin/complaints"
                    style="display:flex; gap:8px; align-items:center; flex-wrap:wrap;">
                <input type="hidden" name="requestId" value="<%= r.getId() %>"/>

                <select name="staffId" <%= isDone ? "disabled" : "required" %>>
                  <option value="">-- Select Staff --</option>
                  <%
                    if (staffList != null) {
                      for (Staff s : staffList) {
                  %>
                    <option value="<%= s.getId() %>">
                      <%= s.getId() %> - <%= s.getUsername() %> (<%= s.getDepartment() %>)
                    </option>
                  <%
                      }
                    }
                  %>
                </select>

                <button class="btn btn-primary" type="submit" <%= isDone ? "disabled" : "" %>>
                  Assign
                </button>

                <% if (isDone) { %>
                  <span style="font-size:12px; opacity:0.8;">Closed</span>
                <% } %>
              </form>
            </td>
          </tr>
          <%
              }
            } else {
          %>
          <tr>
            <td colspan="8">No complaints found.</td>
          </tr>
          <% } %>

        </table>
      </div>
    </div>

  </div>
</div>
</body>
</html>
