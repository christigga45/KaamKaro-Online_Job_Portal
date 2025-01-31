<%@page import="model.Job"%>

<%@ page import="model.User" %>

<%
if (session.getAttribute("adminId") == null) {
    response.sendRedirect("adminLogin.jsp?error=Please login to access the dashboard.");
    return;
}
    Job jobListing = (Job) request.getAttribute("jobListing");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= jobListing.getJobTitle()%> | Job Details</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <div class="container">
            <div class="logo">KaamKaro</div>
            <ul class="nav-links">
                <li><a href="manageJobs.jsp">Manage Jobs</a></li>
                <li><a href="adminLogout">Logout</a></li>
            </ul>
        </div>
    </div>

    <!-- Job Details Section -->
    <div class="job-details-section">
        <div class="manage-jobs-container">
            <div class="job-header">
                <h1><%= jobListing.getJobTitle() %></h1>
                <p><%= jobListing.getJobCompany() %> - <%= jobListing.getJobLocation() %></p>
                <small>Posted on: <%= jobListing.getJobPostedDate() %></small>
            </div>

            <div class="job-info">
                <h2>Job Details</h2>
                <p><strong>Company:</strong> <%= jobListing.getJobCompany() %></p>
                <p><strong>Location:</strong> <%= jobListing.getJobLocation() %></p>
                <p><strong>Skills Required:</strong> <%= jobListing.getJobSkills() %></p>
            </div>

            <div class="job-description">
                <h2>Job Description</h2>
                <p><%= jobListing.getJobDescription() %></p>
            </div>

            <div class="apply-btn-wrapper">
                <a href="EditJobServlet?jobId=<%= jobListing.getJobId() %>" class="manage-cta-btn">Edit</a>&nbsp&nbsp&nbsp    
                <form action="DeleteJobServlet" method="post" style="display:inline;">
                    <input type="hidden" name="jobId" value="<%= jobListing.getJobId() %>">
                    <button type="submit" class="manage-cta-btn-del">Delete</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>
</body>
</html>
