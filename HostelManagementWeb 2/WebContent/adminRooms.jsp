
<jsp:include page="/partials/logout.jsp" />

<%@ page import="java.util.*" %>
<%@ page import="com.hostel.model.Room" %>
<%
    List<Room> rooms = (List<Room>) request.getAttribute("rooms");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Rooms Management</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/style.css">
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 12px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background: #f5f5f5; }
        .row-actions { display: flex; gap: 8px; }
        .inline { display: inline; }
    </style>
</head>
<body>

<h2>Admin - Rooms Management</h2>

<!-- Add Room Form -->
<form method="post" action="<%=request.getContextPath()%>/admin/rooms">
    <input type="hidden" name="action" value="add"/>

    Room No:
    <input name="roomNo" required placeholder="A101"/>

    Hostel:
    <select name="hostelType" required>
        <option value="MALE">MALE</option>
        <option value="FEMALE">FEMALE</option>
    </select>

    Allowed Level:
    <select name="allowedLevel" required>
        <option value="ANY">ANY</option>
        <option value="FIRST_YEAR">FIRST_YEAR</option>
        <option value="SENIOR">SENIOR</option>
        <option value="FINAL_YEAR">FINAL_YEAR</option>
    </select>

    Floor:
    <input name="floor" type="number" required min="0" value="0"/>

    Capacity:
    <input name="capacity" type="number" required min="1" value="4"/>

    Status:
    <select name="status" required>
        <option value="AVAILABLE">AVAILABLE</option>
        <option value="MAINTENANCE">MAINTENANCE</option>
        <option value="CLOSED">CLOSED</option>
    </select>

    <button type="submit">Add Room</button>
</form>

<table>
    <tr>
        <th>Room</th>
        <th>Hostel</th>
        <th>Allowed Level</th>
        <th>Floor</th>
        <th>Capacity</th>
        <th>Occupied</th>
        <th>Available Beds</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>

    <%
        if (rooms != null) {
            for (Room r : rooms) {
    %>
    <tr>
        <form method="post" action="<%=request.getContextPath()%>/admin/rooms">
            <input type="hidden" name="action" value="update"/>

            <td>
                <input name="roomNo" value="<%=r.getRoomNo()%>" readonly/>
            </td>

            <td>
                <select name="hostelType">
                    <option value="MALE" <%= "MALE".equals(r.getHostelType()) ? "selected" : "" %>>MALE</option>
                    <option value="FEMALE" <%= "FEMALE".equals(r.getHostelType()) ? "selected" : "" %>>FEMALE</option>
                </select>
            </td>

            <td>
                <select name="allowedLevel">
                    <option value="ANY" <%= "ANY".equals(r.getAllowedLevel()) ? "selected" : "" %>>ANY</option>
                    <option value="FIRST_YEAR" <%= "FIRST_YEAR".equals(r.getAllowedLevel()) ? "selected" : "" %>>FIRST_YEAR</option>
                    <option value="SENIOR" <%= "SENIOR".equals(r.getAllowedLevel()) ? "selected" : "" %>>SENIOR</option>
                    <option value="FINAL_YEAR" <%= "FINAL_YEAR".equals(r.getAllowedLevel()) ? "selected" : "" %>>FINAL_YEAR</option>
                </select>
            </td>

            <td><input name="floor" type="number" min="0" value="<%=r.getFloor()%>"/></td>
            <td><input name="capacity" type="number" min="1" value="<%=r.getCapacity()%>"/></td>

            <td><%=r.getOccupied()%></td>
            <td><%=r.getAvailableBeds()%></td>

            <td>
                <select name="status">
                    <option value="AVAILABLE" <%= "AVAILABLE".equals(r.getStatus()) ? "selected" : "" %>>AVAILABLE</option>
                    <option value="MAINTENANCE" <%= "MAINTENANCE".equals(r.getStatus()) ? "selected" : "" %>>MAINTENANCE</option>
                    <option value="CLOSED" <%= "CLOSED".equals(r.getStatus()) ? "selected" : "" %>>CLOSED</option>
                </select>
            </td>

            <td class="row-actions">
                <button type="submit">Update</button>
        </form>

        <form method="post" action="<%=request.getContextPath()%>/admin/rooms" class="inline">
            <input type="hidden" name="action" value="delete"/>
            <input type="hidden" name="roomNo" value="<%=r.getRoomNo()%>"/>
            <button type="submit" onclick="return confirm('Delete room <%=r.getRoomNo()%>?');">Delete</button>
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
	