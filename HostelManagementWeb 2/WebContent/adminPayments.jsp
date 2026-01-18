
<jsp:include page="/partials/logout.jsp" />

<%@ page import="java.util.*, com.hostel.model.Payment" %>
<%
  String ctx = request.getContextPath();
  List<Payment> payments = (List<Payment>) request.getAttribute("payments");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Admin - Payment Verification</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/style.css">
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <h1>Admin/Warden - Payment Verification</h1>
        <p>Verify student hostel payments and activate accounts</p>

        <div style="margin-top:10px; display:flex; gap:10px; flex-wrap:wrap;">
          <a class="btn btn-primary" href="<%=ctx%>/admin/complaints">Complaints</a>
          <a class="btn btn-primary" href="<%=ctx%>/admin/rooms">Rooms</a>
          <a class="btn btn-primary" href="<%=ctx%>/admin/leaves">Leaves</a>
          <a class="btn btn-primary" href="<%=ctx%>/admin/payments">Payments</a>
        </div>
      </div>

      <a class="btn btn-danger" href="<%=ctx%>/logout">Logout</a>
    </div>

    <div class="card">
      <h2>All Payment Submissions</h2>

      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Student</th>
            <th>Amount</th>
            <th>Method</th>
            <th>Reference</th>
            <th>Date</th>
            <th>Status</th>
            <th>Receipt</th>
            <th>Action</th>
          </tr>

          <%
            if (payments != null && !payments.isEmpty()) {
              for (Payment p : payments) {
                boolean pending = "PENDING".equalsIgnoreCase(p.getStatus());
          %>
          <tr>
            <td><%= p.getId() %></td>
            <td><%= (p.getStudentUsername()==null ? p.getStudentId() : p.getStudentUsername()) %></td>
            <td><%= p.getAmount() %></td>
            <td><%= p.getPaymentMethod() %></td>
            <td><%= p.getReferenceNo() %></td>
            <td><%= p.getPaymentDate() %></td>
            <td><%= p.getStatus() %></td>

            <td>
              <% if (p.getProofPath() != null) { %>
                <a href="<%=ctx%>/<%=p.getProofPath()%>" target="_blank">View</a>
              <% } else { %>
                -
              <% } %>
            </td>

            <td>
              <% if (pending) { %>
              <form method="post" action="<%=ctx%>/admin/payments" style="display:flex; gap:8px; align-items:center;">
                <input type="hidden" name="paymentId" value="<%=p.getId()%>">
                <input type="hidden" name="studentId" value="<%=p.getStudentId()%>">

                <select name="status" required>
                  <option value="VERIFIED">VERIFY</option>
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
          <tr>
            <td colspan="9">No payments found.</td>
          </tr>
          <% } %>

        </table>
      </div>
    </div>

  </div>
</div>
</body>
</html>
