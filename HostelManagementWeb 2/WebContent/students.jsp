
<jsp:include page="/partials/logout.jsp" />

<%@ page import="java.util.*, com.hostel.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            padding: 20px;
        }
        h2, h3 {
            text-align: center;
        }
        form {
            margin-bottom: 20px;
            text-align: center;
        }
        input, button {
            padding: 6px;
            margin: 4px;
        }
        table {
            margin: auto;
            border-collapse: collapse;
            background: #fff;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }
        th {
            background: #007bff;
            color: white;
        }
        button {
            background: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>

<h2>Student Management (CRUD)</h2>

<!-- ADD STUDENT -->
<h3>Add Student</h3>
<form method="post" action="students">
    <input type="hidden" name="action" value="add">

    ID:
    <input type="number" name="id" required>

    Username:
    <input type="text" name="username" required>

    Password:
    <input type="text" name="password" required>

    Room No:
    <input type="text" name="roomNo" required>

    <button type="submit">Add Student</button>
</form>

<hr>

<!-- STUDENT LIST -->
<h3>Student List</h3>

<table>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Room</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students != null) {
        for (Student s : students) {
%>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getUsername() %></td>
        <td><%= s.getRoomNo() %></td>

        <!-- UPDATE -->
        <td>
            <form method="post" action="students">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= s.getId() %>">

                <input type="text" name="username" value="<%= s.getUsername() %>" required>
                <input type="text" name="password" value="<%= s.getPassword() %>" required>
                <input type="text" name="roomNo" value="<%= s.getRoomNo() %>" required>

                <button type="submit">Update</button>
            </form>
        </td>

        <!-- DELETE -->
        <td>
            <form method="post" action="students">
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
