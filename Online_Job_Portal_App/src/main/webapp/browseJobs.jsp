<%@page import="dao.JobDAO"%>
<%@page import="dao.JobApplicationDAO"%>
<%@page import="model.Job"%>
<%@page import="model.User"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>

<%
    // Get the logged-in user from session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("userLogin.jsp?error=Please login to access the dashboard.");
        return;
    }

    // Initialize DAO objects
    JobDAO jobDAO = new JobDAO();
    JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
    List<Job> jobList = jobDAO.getAllJobs();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Jobs</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <div class="container">
            <div class="logo">KaamKaro</div>
            <ul class="nav-links">
                <li><a href="userDashboard.jsp">Dashboard</a></li>
                <li><a href="userLogout">Logout</a></li>
            </ul>
        </div>
    </div>

    <section>
    <div class="manage-jobs">
        <div class="manage-container">
            <h2>Search Jobs</h2>

            <!-- Search Bar -->
            <form action="UserSearchJobServlet" class="search-form">
                <input type="text" name="query" placeholder="Search Jobs by skill, location, or company" class="search-input">
                <button type="submit" class="search-btn">Search</button>
                <input type="hidden" name="action" value="query">
            </form>

            <!-- Job Listings Table -->
            <table>
                <thead>
                    <tr>
                        <th>Job ID</th>
                        <th>Title</th>
                        <th>Company</th>
                        <th>Location</th>
                        <th>Skills</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (jobList != null && !jobList.isEmpty()) {
                            for (Job job : jobList) {
                                // Check if the user has applied for the job
                                boolean hasApplied = jobApplicationDAO.hasUserAppliedForJob(user.getApplicantId(), (int) job.getJobId());
                    %>
                    <tr>
                        <td><%= job.getJobId() %></td>
                        <td><a href="userJobDetailsServlet?job_id=<%= job.getJobId() %>"><%= job.getJobTitle() %></a></td>
                        <td><%= job.getJobCompany() %></td>
                        <td><%= job.getJobLocation() %></td>
                        <td><%= job.getJobSkills() %></td>
                        <td>
                            <!-- Apply or Applied Button based on application status -->
                            <% if (hasApplied) { %>
                                <button class="manage-cta-apply-btn" disabled>Applied</button>
                            <% } else { %>
                                <form action="applyJob.jsp?jobId=<%= job.getJobId() %>&userId=<%= user.getApplicantId() %>" method="post" style="display:inline;">
                                    <input type="hidden" name="jobId" value="<%= job.getJobId() %>">
                                    <button type="submit" class="manage-cta-btn">Apply</button>
                                </form>
                            <% } %>
                        </td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" style="text-align: center; color: #999;">No jobs found.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    </section>

    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>

</body>
</html>
