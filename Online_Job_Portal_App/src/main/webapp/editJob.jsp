<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Job" %>
<%@ page import="dao.JobDAO" %>

<%
    // Fetch the jobId from the request parameter
     if (session.getAttribute("adminId") == null) {
        response.sendRedirect("adminLogin.jsp?error=Please login to access the dashboard.");
        return;
    }

    
       Job job = (Job)request.getAttribute("job"); // Fetch the job details using DAO
        

    if (job == null) {
        out.println("<h2>Invalid Job ID or Job not found.</h2>");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Job</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Navbar Section -->
    <div class="navbar">
        <div class="container">
            <div class="logo">
                KaamKaro
            </div>
            <ul class="nav-links">
                <li><a href="manageJobs.jsp">Manage Jobs</a></li>
                <li><a href="adminLogout">Logout</a></li>
            </ul>
        </div>
    </div>

    <div class="edit-job-container">
    <!-- Edit Job Form Section -->
    <div class="edit-job-form">
        <h2>Edit Job</h2>

        <!-- Job Edit Form -->
        <form action="EditJobServlet?jobId=<%=job.getJobId() %>" method="post">
            <input type="hidden" name="jobId" value="<%= job.getJobId() %>" />
            <input type="hidden" name="jobPostedDate" value="<%= job.getJobPostedDate() %>" />

            <label for="jobTitle">Job Title</label>
            <input type="text" id="jobTitle" name="jobTitle" value="<%= job.getJobTitle() %>" required />

            <label for="jobDescription">Job Description</label>
            <textarea id="jobDescription" name="jobDescription" required><%= job.getJobDescription() %></textarea>

            <label for="jobCompany">Company</label>
            <input type="text" id="jobCompany" name="jobCompany" value="<%= job.getJobCompany() %>" required />

            <label for="jobLocation">Job Location</label>
            <input type="text" id="jobLocation" name="jobLocation" value="<%= job.getJobLocation() %>" required />

            <label for="jobSkills">Job Skills</label>
            <input type="text" id="jobSkills" name="jobSkills" value="<%= job.getJobSkills() %>" required />


            <button type="submit">Update Job</button>
        </form>
    </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>
</body>
</html>
