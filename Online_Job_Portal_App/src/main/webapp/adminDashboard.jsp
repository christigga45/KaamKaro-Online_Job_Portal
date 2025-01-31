<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("adminId") == null) {
        response.sendRedirect("adminLogin.jsp?error=Please login to access the dashboard.");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="container">
            <div class="logo">KaamKaro</div>
            <ul class="nav-links">
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="manageJobs.jsp">Manage Jobs</a></li>
                <li><a href="adminViewApplications">View Applications</a></li>
                <li><a href="adminLogout">Logout</a></li>
            </ul>
        </div>
    </nav>

    <!-- Admin Welcome Section -->
    <section class="admin-dashboard">
        <h2>Welcome, <%= session.getAttribute("adminUsername") %>!</h2>
        <p>As an admin, you can manage job listings, view job applications, and more.</p>

        <!-- Quick Links -->
        <div class="quick-links">
            <a href="manageJobs.jsp" class="cta-btn">Manage Job Listings</a>
            <a href="adminViewApplications" class="cta-btn">View Job Applications</a>
        </div><br><br>

        <!-- Footer -->
        <footer>
            <p>&copy; 2025 KaamKaro. All rights reserved.</p>
        </footer>
    </section>
</body>
</html>
