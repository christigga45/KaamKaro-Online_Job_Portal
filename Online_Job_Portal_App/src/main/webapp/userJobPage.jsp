
<%@page import="model.Job"%>
<%@ page import="dao.JobApplicationDAO" %>
<%@ page import="model.User" %>

<%

User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect("userLogin.jsp?error=Please login to access the dashboard.");
    return;
}
    Job job = (Job) request.getAttribute("jobListing");
Boolean hasAppliedObj = (Boolean)request.getAttribute("hasApplied");
boolean hasApplied = (hasAppliedObj != null) ? hasAppliedObj : false;

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= job.getJobId() %> | Job Details</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <div class="container">
            <div class="logo">KaamKaro</div>
            <ul class="nav-links">
                <li><a href="#about">About</a></li>
                <li><a href="userDashboard.jsp">Dashboard</a></li>
                <li><a href="userLogout">Logout</a></li>
            </ul>
        </div>
    </div>

    <!-- Job Details Section -->
    <div class="job-details-section">
        <div class="manage-jobs-container">
            <div class="job-header">
                <h1><%= job.getJobTitle() %></h1>
                <p><%= job.getJobCompany() %> - <%= job.getJobLocation() %></p>
                <small>Posted on: <%= job.getJobPostedDate() %></small>
            </div>

            <div class="job-info">
                <h2>Job Details</h2>
                <p><strong>Company:</strong> <%= job.getJobCompany() %></p>
                <p><strong>Location:</strong> <%= job.getJobLocation() %></p>
                <p><strong>Skills Required:</strong> <%= job.getJobSkills() %></p>
            </div>

            <div class="job-description">
                <h2>Job Description</h2>
                <p><%= job.getJobDescription() %></p>
            </div>

            <div class="apply-btn-wrapper">
                <% if (hasApplied) { %>
                    <button class="manage-cta-apply-btn" disabled>Applied</button>
                <% } else { %>
                    <form action="ApplyJobServlet?jobId=<%= job.getJobId() %>&userId=<%= user.getApplicantId() %>" method="post" style="display:inline;">
                        <input type="hidden" name="jobId" value="<%= job.getJobId() %>">
                        <button type="submit" class="manage-cta-btn">Apply</button>
                    </form>
                <% } %>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>
</body>
</html>
