<%
  String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <title>University Hostel Portal</title>
  <link rel="stylesheet" href="<%=ctx%>/assets/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<!-- Top Bar (like WUSL style) -->
<header class="site-header">
  <div class="wrap header-inner">
    <div class="brand-left">
      <div class="logo-badge">W</div>
      <div>
        <div class="brand-title">University Hostel Portal</div>
        <div class="brand-sub">Residence - Services - Student Welfare</div>
      </div>
    </div>

    <nav class="header-links">
      <a href="#about">About</a>
      <a href="#vision">Vision</a>
      <a href="#news">Announcements</a>
      <a href="#gallery">Gallery</a>
      <a href="#contact">Contact</a>
    </nav>

    <div class="header-actions">
      <a class="btn btn-primary" href="<%=ctx%>/login.jsp">Login</a>
      <a class="btn btn-light" href="<%=ctx%>/register.jsp">Create Account</a>
    </div>
  </div>
</header>

<!-- Hero -->
<section class="hero">
  <div class="wrap hero-inner">
    <div class="hero-text">
      <div class="hero-kicker">Wayamba University - Hostel Management</div>
      <h1>Welcome to the Hostel Management System</h1>
      <p>
        Submit complaints, request leave, complete hostel payments, and select rooms online.
        Faster service, better tracking, and improved student welfare.
      </p>

      <div class="hero-actions">
        <a class="btn btn-primary" href="<%=ctx%>/login.jsp">Go to Login</a>
        <a class="btn btn-light" href="#contact">Contact Hostel Office</a>
      </div>

      <div class="chips">
        <span class="chip">Secure Login</span>
        <span class="chip">Complaint Workflow</span>
        <span class="chip">Leave Approval</span>
        <span class="chip">Payment Verification</span>
        <span class="chip">Room Allocation</span>
      </div>
    </div>

    
  </div>
</section>

<!-- About / History -->
<section class="section" id="about">
  <div class="wrap">
    <div class="section-title">
      <h2>University & Hostel Services</h2>
      <p class="muted">
        Our hostel system supports a safe, disciplined, and comfortable residence environment for students.
      </p>
    </div>

    <div class="grid-3">
      <div class="card-box">
        <div class="tag">History</div>
        <h3>University History</h3>
        <p>
          A proud tradition of academic excellence and student development. Hostel services ensure wellbeing,
          safety, and responsible living.
        </p>
      </div>

      <div class="card-box" id="vision">
        <div class="tag">Vision</div>
        <h3>Our Vision</h3>
        <p>To provide a safe, inclusive, and supportive hostel experience for every student.</p>
      </div>

      <div class="card-box">
        <div class="tag">Mission</div>
        <h3>Our Mission</h3>
        <p>Deliver reliable hostel services through transparent processes and digital management.</p>
      </div>
    </div>
  </div>
</section>

<!-- Achievements -->
<section class="section soft">
  <div class="wrap">
    <div class="section-title">
      <h2>Special Achievements</h2>
      
    </div>

    <div class="grid-3">
      <div class="news-card">
        <div class="tag">Award</div>
        <h3>Student Welfare Excellence</h3>
        <p>Recognized for structured welfare and hostel management standards.</p>
      </div>
      <div class="news-card">
        <div class="tag">Program</div>
        <h3>Green Hostel Initiative</h3>
        <p>Promoting cleanliness, sustainability, and responsible living.</p>
      </div>
      <div class="news-card">
        <div class="tag">Service</div>
        <h3>Fast Maintenance Response</h3>
        <p>Complaint workflow improves response time and accountability.</p>
      </div>
    </div>
  </div>
</section>

<!-- Announcements -->
<section class="section" id="news">
  <div class="wrap">
    <div class="section-title">
      <h2>Special Announcements</h2>
      
    </div>

    <div class="grid-3">
      <div class="news-card">
        <div class="tag">Notice</div>
        <h3>Hostel Inspection</h3>
        <p>Room inspections will be conducted this week. Keep rooms tidy.</p>
      </div>
      <div class="news-card">
        <div class="tag">Event</div>
        <h3>Community Meeting</h3>
        <p>All residents must attend Friday 7 PM at the common hall.</p>
      </div>
      <div class="news-card">
        <div class="tag">Maintenance</div>
        <h3>Water Supply Update</h3>
        <p>Plumbing maintenance: 9 AM to 12 PM</p>
      </div>
    </div>
  </div>
</section>

<!-- Gallery -->
<section class="section soft" id="gallery">
  <div class="wrap">
    <div class="section-title">
      <h2>University Gallery</h2>
     
    </div>

    <div class="gallery">
      <img src="<%=ctx%>/assets/g1.jpg" alt="Gallery 1">
      <img src="<%=ctx%>/assets/g2.jpg" alt="Gallery 2">
      <img src="<%=ctx%>/assets/g3.jpg" alt="Gallery 3">
      <img src="<%=ctx%>/assets/g4.jpg" alt="Gallery 4">
    </div>
  </div>
</section>

<!-- Contact -->
<section class="section" id="contact">
  <div class="wrap">
    <div class="section-title">
      <h2>Contact Us</h2>
      <p class="muted">Hostel office contacts and location.</p>
    </div>

    <div class="grid-2">
      <div class="card-box">
        <h3>Warden Office</h3>
        <p><b>Phone:</b> +94 60 1234 567</p>
        <p><b>Email:</b> hostel@university.lk</p>
        <p><b>Hours:</b> 8:30 AM to 4:30 PM</p>
      </div>

      <div class="card-box">
        <h3>Address</h3>
        <p>University Hostel, Wayamba University , Kuliyapitiya Premises ,  Sri Lanka</p>
        <a class="btn btn-primary" href="<%=ctx%>/login.jsp">Login to Portal</a>
      </div>
    </div>
  </div>
</section>

<footer class="footer">
  <div class="wrap footer-inner">
    <div>
      <b>University Hostel Management System</b><br>
      <span class="muted">© <%= java.time.Year.now() %> All rights reserved.</span>
    </div>
    <div class="footer-links">
      <a href="<%=ctx%>/login.jsp">Login</a>
      <a href="<%=ctx%>/register.jsp">Create Account</a>
      <a href="#contact">Contact</a>
    </div>
  </div>
</footer>

</body>
</html>
