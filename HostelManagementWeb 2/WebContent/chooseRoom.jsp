<%@ page import="java.util.List" %>
<%@ page import="com.hostel.model.Room" %>
<%@ page import="com.hostel.model.Student" %>

<%
  String ctx = request.getContextPath();
  List<Room> rooms = (List<Room>) request.getAttribute("rooms");
  Student student = (Student) request.getAttribute("student");

  String msg = (String) request.getAttribute("msg");
  String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Choose Room</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/style.css">
</head>
<body>
<div class="page-overlay">
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <h1>Choose Room</h1>
        <p>Select an available room based on capacity</p>

        <div style="margin-top:10px; display:flex; gap:10px; flex-wrap:wrap;">
          <a class="btn btn-primary" href="<%=ctx%>/student/complaints">Complaints</a>
          <a class="btn btn-primary" href="<%=ctx%>/student/leave">Leave Requests</a>
          <a class="btn btn-primary" href="<%=ctx%>/student/payment">Payments</a>
          <a class="btn btn-primary" href="<%=ctx%>/student/choose-room">Choose Room</a>
        </div>
      </div>

      <a class="btn btn-danger" href="<%=ctx%>/logout">Logout</a>
    </div>

    <div class="card">
      <h2>My Current Room</h2>
      <p>
        Room: <b><%= (student == null || student.getRoomNo() == null ? "Not Assigned" : student.getRoomNo()) %></b>
      </p>
    </div>

    <div class="card">
      <h2>Available Rooms</h2>

      <% if (msg != null) { %><p style="color:green;"><%=msg%></p><% } %>
      <% if (error != null) { %><p style="color:red;"><%=error%></p><% } %>

      <form method="post" action="<%=ctx%>/student/choose-room" style="display:flex; gap:10px; align-items:end; flex-wrap:wrap;">
        <div>
          <label>Select Room</label>
          <select name="roomNo" required>
            <option value="">-- Select --</option>
            <%
              if (rooms != null) {
                for (Room r : rooms) {
            %>
              <option value="<%=r.getRoomNo()%>">
                <%=r.getRoomNo()%> (Occupied: <%=r.getOccupied()%>/<%=r.getCapacity()%>)
              </option>
            <%
                }
              }
            %>
          </select>
        </div>

        <button class="btn btn-primary" type="submit">Allocate</button>
      </form>

      <p style="margin-top:10px; font-size: 0.95em;">
        Note: If two students try to select the last bed at the same time, the system locks the room record and prevents double booking.
      </p>
    </div>

  </div>
</div>
</body>
</html>
