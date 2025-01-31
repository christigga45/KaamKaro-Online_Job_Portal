<%@page import="model.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("userLogin.jsp?error=Please login to apply for a job.");
        return;
    }
    System.out.println(user);

    // Retrieve the user ID and job ID from the session or request
    int userId = Integer.parseInt(request.getParameter("userId")) ;
    int jobId = Integer.parseInt(request.getParameter("jobId")) ;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Job Application Form</title>
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
                <li><a href="viewApplications.jsp">My Applications</a></li>
                <li><a href="userLogout">Logout</a></li>
            </ul>
        </div>
    </nav>

    <!-- Job Application Form -->
    <div class="edit-job-container">
    <div class="job-form">
        <h2>Job Application Form</h2>
        <form action="ApplyJobServlet" method="POST">
        
        <p style="color: red;">
                <% 
                   if (request.getParameter("error") != null) { 
                       out.print(request.getParameter("error"));
                   } 
                %>
            </p><br>
            <!-- Hidden userId and jobId fields -->
            <input type="hidden" name="userId" value="<%= userId %>">
            <input type="hidden" name="jobId" value="<%= jobId %>">

            <!-- Personal Information -->
            <h3>Personal Information</h3>
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" required>

            <!-- Education -->
            <h3>Education</h3>
            <label for="education">Education Details (e.g., Degree, Institution, Year):</label>
            <textarea id="education" name="education" rows="3" required></textarea>

            <!-- Projects -->
            <h3>Projects</h3>
            <label for="projects">Projects (e.g., Programming, Design, etc.):</label>
            <textarea id="projects" name="projects" rows="3" required></textarea>

            <!-- Work Experience -->
            <h3>Work Experience</h3>
            <label for="experience">Work Experience Details (e.g., Job Title, Company, Years):</label>
            <textarea id="experience" name="experience" rows="3" required></textarea>

            <!-- Skills -->
            <h3>Skills</h3>
            <label for="skills">Skills (e.g., Programming, Design, etc.):</label>
            <textarea id="skills" name="skills" rows="3" required></textarea>

            <!-- Submit Button -->
            <button type="submit">Submit Application</button>
        </form>
    </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>
</body>
</html>
