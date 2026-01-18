<%@ page import="java.util.List" %>
<%@ page import="com.hostel.model.Payment" %>

<%
    String ctx = request.getContextPath();
    List<Payment> payments = (List<Payment>) request.getAttribute("payments");

    String msg = (String) request.getAttribute("msg");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Student - Hostel Payment</title>
    <link rel="stylesheet" href="<%= ctx %>/assets/style.css">
</head>
<body>
<div class="page-overlay">
    <div class="container">

        <div class="topbar">
            <div class="brand">
                <h1>Hostel Payment</h1>
                <p>Submit payment & track verification status</p>

                <div style="margin-top:10px; display:flex; gap:10px; flex-wrap:wrap;">
                    <a class="btn btn-primary" href="<%= ctx %>/student/complaints">Complaints</a>
                    <a class="btn btn-primary" href="<%= ctx %>/student/leave">Leave Requests</a>
                    <a class="btn btn-primary" href="<%= ctx %>/student/payment">Payments</a>
                    
                    
                </div>
            </div>

            <a class="btn btn-danger" href="<%= ctx %>/logout">Logout</a>
        </div>

        <div class="card">
            <h2>Submit Payment</h2>

            <% if (msg != null) { %>
                <p style="color:green;"><%= msg %></p>
            <% } %>

            <% if (error != null) { %>
                <p style="color:red;"><%= error %></p>
            <% } %>

            <form method="post" action="<%= ctx %>/student/payment" enctype="multipart/form-data">
                <div class="form-row">

                    <div>
                        <label>Amount</label>
                        <input type="number" step="0.01" name="amount" required>
                    </div>

                    <div>
                        <label>Method</label>
                        <select name="method" required>
                            <option value="Bank Deposit">Bank Deposit</option>
                            <option value="Online Transfer">Online Transfer</option>
                            <option value="Cash (Office)">Cash (Office)</option>
                        </select>
                    </div>

                    <div>
                        <label>Reference No</label>
                        <input type="text" name="ref" required>
                    </div>

                    <div>
                        <label>Date</label>
                        <input type="date" name="date" required>
                    </div>

                    <div>
                        <label>Receipt (optional)</label>
                        <input type="file" name="proof" accept=".jpg,.jpeg,.png,.pdf">
                    </div>

                    <div style="align-self:end;">
                        <button class="btn btn-primary" type="submit">Submit</button>
                    </div>

                </div>
            </form>
        </div>

        <div class="card">
            <h2>My Payment History</h2>
            <div class="table-wrap">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Amount</th>
                        <th>Method</th>
                        <th>Reference</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Receipt</th>
                    </tr>

                    <%
                        if (payments != null && !payments.isEmpty()) {
                            for (Payment p : payments) {
                    %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getAmount() %></td>
                        <td><%= p.getPaymentMethod() %></td>
                        <td><%= p.getReferenceNo() %></td>
                        <td><%= p.getPaymentDate() %></td>
                        <td><%= p.getStatus() %></td>

                        <td>
                            <% if (p.getProofPath() != null && !p.getProofPath().isEmpty()) { %>
                                <a href="<%= ctx %>/<%= p.getProofPath() %>" target="_blank">View</a>
                            <% } else { %>
                                -
                            <% } %>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="7">No payment records found.</td>
                    </tr>
                    <% } %>

                </table>
            </div>
        </div>

    </div>
</div>
</body>
</html>
