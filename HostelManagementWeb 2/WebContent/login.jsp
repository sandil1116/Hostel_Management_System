<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
    String err = request.getParameter("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Login | Hostel Management System</title>
<style>
    body{
        margin:0;
        font-family: 'Segoe UI', Arial, sans-serif;
        min-height:100vh;
        display:flex;
        align-items:center;
        justify-content:center;

        /* Background image */
        background:
            linear-gradient(
                rgba(0,0,0,0.55),
                rgba(0,0,0,0.55)
            ),
            url("<%= request.getContextPath() %>/assets/G2-Medium.jpg");
        background-size: cover;
        background-position: center;
    }

    .wrap{
        width:440px;
        background: rgba(255,255,255,0.95);
        border-radius:16px;
        box-shadow: 0 15px 40px rgba(0,0,0,0.35);
        overflow:hidden;
        animation: fadeIn 0.7s ease;
    }

    @keyframes fadeIn {
        from { opacity:0; transform: translateY(20px); }
        to   { opacity:1; transform: translateY(0); }
    }

    .header{
        padding:26px 22px 12px;
        text-align:center;
    }

    .header h2{
        margin:0;
        font-size:26px;
        letter-spacing:0.5px;
        color:#111827;
    }

    .tabs{
        display:flex;
        gap:10px;
        padding:0 22px 12px;
        justify-content:center;
        flex-wrap:wrap;
    }

    .tab{
        border:1px solid #d0d7de;
        padding:10px 16px;
        border-radius:999px;
        cursor:pointer;
        font-weight:700;
        background:#fff;
        transition: all 0.25s ease;
    }

    .tab:hover{
        background:#f1f5f9;
    }

    .tab.active{
        background:#111827;
        color:#fff;
        border-color:#111827;
    }

    .content{
        padding:10px 26px 26px;
    }

    .hint{
        color:#555;
        font-size:13px;
        margin:6px 0 18px;
        text-align:center;
    }

    .field{
        margin-bottom:14px;
    }

    label{
        display:block;
        font-size:13px;
        color:#374151;
        margin-bottom:6px;
    }

    input{
        width:100%;
        padding:11px 14px;
        border:1px solid #d1d5db;
        border-radius:12px;
        outline:none;
        font-size:14px;
        transition: all 0.2s ease;
    }

    input:focus{
        border-color:#2563eb;
        box-shadow: 0 0 0 3px rgba(37,99,235,0.2);
    }

    .btn{
        width:100%;
        padding:12px 14px;
        border:0;
        border-radius:12px;
        background: linear-gradient(135deg,#2563eb,#1d4ed8);
        color:#fff;
        font-weight:800;
        cursor:pointer;
        font-size:15px;
        margin-top:10px;
        transition: all 0.25s ease;
    }

    .btn:hover{
        transform: translateY(-1px);
        box-shadow: 0 8px 18px rgba(37,99,235,0.4);
    }

    .error{
        background:#fee2e2;
        color:#991b1b;
        border:1px solid #fecaca;
        padding:10px 14px;
        border-radius:12px;
        margin:10px 0 14px;
        font-size:13px;
        text-align:center;
    }

    .links{
        margin-top:14px;
        text-align:center;
        font-size:13px;
    }

    .links a{
        color:#2563eb;
        text-decoration:none;
        font-weight:600;
    }

    .footer{
        padding:16px 22px 18px;
        text-align:center;
        font-size:12px;
        color:#6b7280;
        background:#f9fafb;
        border-top:1px solid #eee;
    }

    .footer b{
        color:#111827;
    }
</style>

</head>
<body>

<div class="wrap">
    <div class="header">
        <h2>Login</h2>
    </div>

    <div class="tabs">
        <div class="tab active" id="tabStudent" onclick="setRole('STUDENT')">I'm a Student</div>
        <div class="tab" id="tabStaff" onclick="setRole('STAFF')">I'm Staff</div>
        <div class="tab" id="tabAdmin" onclick="setRole('ADMIN')">I'm Warden</div>
    </div>

    <div class="content">
        <div class="hint">Please fill in your credentials to login.</div>

        <% if ("1".equals(err)) { %>
        <div class="error">Invalid username or password.</div>
        <% } %>

        <!-- MUST match your LoginServlet mapping -->
        <form method="post" action="<%= ctx %>/login">
            <input type="hidden" name="role" id="role" value="STUDENT"/>

            <div class="field">
                <label>Username</label>
                <input type="text" name="username" required />
            </div>

            <div class="field">
                <label>Password</label>
                <input type="password" name="password" required />
            </div>

            <button class="btn" type="submit">Login</button>
        </form>

        <div class="links">
            New student? <a href="<%= request.getContextPath() %>/register.jsp">Create Account</a>

        <div class="back-home-wrap">
  <a href="<%=request.getContextPath()%>/" class="back-home-btn">
    ← Back to Home
  </a>
</div>

    </div>

    <div class="footer">
        <b>Hostel Management System</b><br/>
        Wayamba University Hostel & Maintenance Portal<br/>
        © <%= java.time.Year.now() %> All Rights Reserved.
    </div>
</div>

<script>
    function setRole(role){
        document.getElementById("role").value = role;

        document.getElementById("tabStudent").classList.remove("active");
        document.getElementById("tabStaff").classList.remove("active");
        document.getElementById("tabAdmin").classList.remove("active");

        if(role === "STUDENT") document.getElementById("tabStudent").classList.add("active");
        if(role === "STAFF") document.getElementById("tabStaff").classList.add("active");
        if(role === "ADMIN") document.getElementById("tabAdmin").classList.add("active");
    }
</script>

</body>
</html>
