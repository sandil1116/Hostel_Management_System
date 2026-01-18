<%@ page import="java.util.List" %>
<%@ page import="com.hostel.model.Staff" %>

<%
    // This name MUST match what StaffServlet sets
    List<Staff> staffList = (List<Staff>) request.getAttribute("staffList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Staff Management</title>
</head>
<body>

<h2>Staff Management (Admin)</h2>

<!-- ADD STAFF -->
<h3>Add Staff</h3>
<form method="post" action="<%= request.getContextPath() %>/staff">
    <input type="hidden" name="action" value="add">

    ID:
    <input type="number" name="id" required><br><br>

    Username:
    <input type="text" name="username" required><br><br>

    Password:
    <input type="text" name="password" required><br><br>

    Department:
    <input type="text" name="department" required><br><br>

    <button type="submit">Add Staff</button>
</form>

<hr>

<!-- STAFF LIST -->
<h3>Staff List</h3>

<table border="1" cellpadding="8">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Department</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>

<%
    if (staffList != null) {
        for (Staff s : staffList) {
%>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getUsername() %></td>
        <td><%= s.getDepartment() %></td>

        <!-- UPDATE -->
        <td>
            <form method="post" action="<%= request.getContextPath() %>/staff">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= s.getId() %>">

                <input type="text" name="username" value="<%= s.getUsername() %>" required>
                <input type="text" name="password" value="<%= s.getPassword() %>" required>
                <input type="text" name="department" value="<%= s.getDepartment() %>" required>

                <button type="submit">Update</button>
            </form>
        </td>

        <!-- DELETE -->
        <td>
            <form method="post" action="<%= request.getContextPath() %>/staff">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= s.getId() %>">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
<%
        }
    }
%>
</table>

</body>
</html>
