
<jsp:include page="/partials/logout.jsp" />

<%@ page import="java.util.*, com.hostel.model.MaintenanceRequest" %>
<%
    List<MaintenanceRequest> list = (List<MaintenanceRequest>) request.getAttribute("list");
    String ctx = request.getContextPath();
%>

   
</a>

<!DOCTYPE html>
<html>

<head>
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="<%= ctx %>/assets/style.css">
    <style>
/* Kill the template decorative dots (pseudo-elements) */
.topbar::before, .topbar::after,
.brand::before, .brand::after,
.container::before, .container::after,
.page-overlay::before, .page-overlay::after,
body::before, body::after,
html::before, html::after {
  display: none !important;
  content: none !important;
}

/* Extra safety: stop overflow dots from showing */
.topbar, .brand, .container, .page-overlay {
  overflow: hidden;
}
</style>
    
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
  <div class="brand">
    <h1>Student Dashboard</h1>
    <p>Submit hostel complaints & track progress</p>
  </div>

  <div style="margin-top:10px; display:flex; gap:10px; flex-wrap:wrap;">
    <a class="btn btn-primary" href="<%= ctx %>/student/complaints">Complaints</a>
    <a class="btn btn-primary" href="<%= ctx %>/student/leave">Leave Requests</a>
    <a class="btn btn-primary" href="<%= ctx %>/student/payment">Payments</a>
    <a class="btn btn-primary" href="<%= ctx %>/student/choose-room">Choose Room</a>
    
</div>
</div>

    <div class="card">
      <h2>Submit New Complaint</h2>

      <form method="post" action="<%= ctx %>/student/complaints">
        <div class="form-row">

          <div>
            <label>Room No</label>
            <input type="text" name="roomNo" required>
          </div>

          <div>
            <label>Category</label>
            <select name="category" required>
              <option value="Cleaning">Cleaning</option>
              <option value="Plumbing">Plumbing</option>
              <option value="Electricity">Electricity</option>
            </select>
          </div>

          <div>
            <label>Description</label>
            <input type="text" name="description" required placeholder="Describe the issue clearly">
          </div>

          <div>
            <button class="btn btn-primary" type="submit">Submit</button>
          </div>

        </div>
      </form>
    </div>

    <div class="card">
      <h2>My Complaints</h2>
      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Room</th>
            <th>Category</th>
            <th>Description</th>
            <th>Status</th>
            <th>Assigned Staff</th>
            <th>Staff Note</th>
            <th>Created</th>
          </tr>

          <%
            if (list != null && !list.isEmpty()) {
              for (MaintenanceRequest r : list) {
                String st = r.getStatus() == null ? "PENDING" : r.getStatus();
                String badge = "badge-pending";
                if ("ASSIGNED".equalsIgnoreCase(st)) badge="badge-assigned";
                if ("ONGOING".equalsIgnoreCase(st)) badge="badge-ongoing";
                if ("DONE".equalsIgnoreCase(st)) badge="badge-done";
          %>
          <tr>
            <td><%= r.getId() %></td>
            <td><%= r.getRoomNo() %></td>
            <td><%= r.getCategory() %></td>
            <td><%= r.getDescription() %></td>
            <td><span class="badge <%= badge %>"><%= st %></span></td>
            <td><%= (r.getAssignedStaffId()==null ? "-" : r.getAssignedStaffId()) %></td>
            <td><%= (r.getStaffNote()==null ? "-" : r.getStaffNote()) %></td>
            <td><%= r.getCreatedAt() %></td>
          </tr>
          <%
              }
            } else {
          %>
          <tr>
            <td colspan="8">No complaints submitted yet.</td>
          </tr>
          <% } %>

        </table>
      </div>
    </div>

  </div>
</div>
</body>
</html>
