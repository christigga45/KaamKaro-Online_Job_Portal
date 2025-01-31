<%@page import="dao.UserDAO"%>
<%@page import="dao.JobApplicationDAO"%>
<%@page import="model.JobApplication"%>
<%@page import="dao.JobDAO"%>
<%@page import="model.Job"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("adminId") == null) {
        response.sendRedirect("adminLogin.jsp?error=Please login to access the dashboard.");
        return;
    }

    // DAO to fetch application data
    List<JobApplication> applications = (List<JobApplication>)request.getAttribute("jobApplications");

    // DAO for job details
    JobDAO jobDAO = new JobDAO();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin View Applications</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="navbar">
        <div class="container">
            <div class="logo">KaamKaro </div>
            <ul class="nav-links">
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="adminLogout">Logout</a></li>
            </ul>
        </div>
    </div>

    <div class="job-details-section">
        <div class="manage-container">
            <h2 class="about">Applications Overview</h2>

            <table class="styled-table">
                <thead>
                    <tr>
                        <th>Company Name</th>
                        <th>Applicant Name</th>
                        <th>Job Title</th>
                        <th>Location</th>
                        <th>Skills</th>
                        <th>Application Status</th>
                        <th>View Resume</th>
                        <th>Change Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (applications != null && !applications.isEmpty()) {
                            for (JobApplication applicat : applications) {
                                // Retrieve job details using jobId
                                Job job = jobDAO.getJobById(applicat.getJobId());
                           
                                String companyName = job != null ? job.getJobCompany() : "Unknown Company";
                                String jobTitle = job != null ? job.getJobTitle() : "Unknown Job";
                                String location = job != null ? job.getJobLocation() : "Unknown Location";
                                String skills = job != null ? job.getJobSkills() : "Unknown Skills";
                                
                                String status = applicat.getApplicationStatus();
                                String formData = applicat.getFormData(); // Assuming formData is stored as JSON string
                    %>
                    <tr>
                        <td><%= companyName %></td>
                        <td><%=  new UserDAO().getApplicantNameById(applicat.getApplicantId() ) %></td> <!-- Assuming Applicant Name can be fetched from the applicantId -->
                        <td><%= jobTitle %></td>
                        <td><%= location %></td>
                        <td><%= skills %></td>
                        <td><%= status %></td>
                        <td><a href="adminViewApplicantFormData?applicantId=<%= applicat.getApplicantId() %>&companyname=<%=job.getJobCompany() %>" class="manage-cta-apply-btn" >Resume</a></td>
                        <td>
                            <form action="updateApplicationStatus" method="post">
                                <input type="hidden" name="applicationId" value="<%= applicat.getApplicationId() %>">
                                <select name="status" class="manage-cta-select-btn">
                                    <option value="Under Review" <%= status.equals("Under Review") ? "selected" : "" %>>Under Review</option>
                                    <option value="Interview Scheduled" <%= status.equals("Interview Scheduled") ? "selected" : "" %>>Interview Scheduled</option>
                                    <option value="Rejected" <%= status.equals("Rejected") ? "selected" : "" %>>Rejected</option>
                                    <option value="Accepted" <%= status.equals("Accepted") ? "selected" : "" %>>Accepted</option>
                                </select>
                                <button type="submit" class="manage-cta-btn">Update</button>
                            </form>
                        </td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                    <tr>
                        <td colspan="8" style="text-align: center; color: #999;">No applications found.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>

    <script>
 
</body>
</html>
