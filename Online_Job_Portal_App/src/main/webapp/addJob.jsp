<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Add Job</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <div class="container">
        <div class="logo">KaamKaro Admin</div>
        <ul class="nav-links">
            <li><a href="adminDashboard.jsp">Dashboard</a></li>
            <li><a href="manageJobs.jsp">Manage Jobs</a></li>
            <li><a href="adminLogout">Logout</a></li>
        </ul>
    </div>
</div>

<div class="manage-jobs">
    <div class="manage-container">
        <h2>Add Job</h2>
        <form action="AddJobServlet" method="post" class="job-form">
            <!-- Job Title -->
            <label for="jobTitle">Job Title:</label>
            <input type="text" id="jobTitle" name="jobTitle" placeholder="Enter job title" required>

            <!-- Company Name -->
            <label for="jobCompany">Company Name:</label>
            <input type="text" id="jobCompany" name="jobCompany" placeholder="Enter company name" required>

            <!-- Location -->
            <label for="jobLocation">Location:</label>
            <input type="text" id="jobLocation" name="jobLocation" placeholder="Enter job location" required>

            <!-- Skills -->
            <label for="jobSkills">Skills Required:</label>
            <input type="text" id="jobSkills" name="jobSkills" placeholder="Enter required skills (comma-separated)" required>

            <!-- Job Description -->
            <label for="jobDescription">Job Description:</label>
            <textarea id="jobDescription" name="jobDescription" rows="4" placeholder="Enter job description" required></textarea>

            <!-- Submit Button -->
            <button type="submit" class="cta-btn">Add Job</button>
        </form>
    </div>
</div>

<footer>
    <p>© 2025 KaamKaro. All rights reserved.</p>
</footer>
</body>
</html>
