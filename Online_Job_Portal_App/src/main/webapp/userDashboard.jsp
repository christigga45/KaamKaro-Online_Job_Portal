<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	User user=(User)session.getAttribute("user");
    if ( user == null) {
        response.sendRedirect("userLogin.jsp?error=Please login to access the dashboard.");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="container">
            <div class="logo">KaamKaro</div>
            <ul class="nav-links">
                <li><a href="userDashboard.jsp">Dashboard</a></li>
                <li><a href="browseJobs.jsp">Search Jobs</a></li>
                <li><a href="userViewApplicationServlet?userId=<%=user.getApplicantId() %>">My Applications</a></li>
                <li><a href="userLogout">Logout</a></li>
            </ul>
        </div>
    </nav>

    <!-- User Welcome Section -->
    <section class="admin-dashboard">
        <h2>Welcome, <%= user.getUsername() %>!</h2>
        <p>Explore job opportunities, apply to jobs, and manage your applications easily.</p>

        <!-- Quick Links -->
        <div class="quick-links">
            <a href="browseJobs.jsp?username=<%= user.getUsername() %>" class="cta-btn">Search Jobs</a>
            <a href="userViewApplicationServlet?userId=<%=user.getApplicantId() %>" class="cta-btn">My Applications</a>
        </div><br><br>

        <!-- Footer -->
        <footer>
            <p>&copy; 2025 KaamKaro. All rights reserved.</p>
        </footer>
    </section>
</body>
</html>
