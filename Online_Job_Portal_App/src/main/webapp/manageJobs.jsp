<%@page import="dao.JobDAO"%>
<%@page import="model.Job"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    if (session.getAttribute("adminId") == null) {
        response.sendRedirect("adminLogin.jsp?error=Please login to access the dashboard.");
        return;
    }

	String msg=(String)session.getAttribute("msg");
	 if (msg != null) {
	        out.println(msg);
	        session.removeAttribute("msg");  // Remove the message after displaying it
	    }
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Jobs</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <div class="container">
        <div class="logo">KaamKaro Admin</div>
        <ul class="nav-links">
            <li><a href="adminDashboard.jsp">Dashboard</a></li>
            <li><a href="adminLogout">Logout</a></li>
        </ul>
    </div>
</div>

<section>
<div class="manage-jobs">
    <div class="manage-container">
    <h2><% if(msg!=null)
    	out.println(msg); %></h2>
        <h2>Manage Jobs</h2>

        <!-- Search Bar -->
        <form action="adminSearchJobServlet" method="get" class="search-form">
            <input type="text" name="query" placeholder="Search Jobs by skill, location, or company" class="search-input">
            <button type="submit" class="search-btn">Search</button>
            <input type="hidden" name="action" value="query">
        </form>

        <!-- Add Job Button -->
        <a href="addJob.jsp" class="cta-btn">+ Add Job</a>

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
             	JobDAO dao=new JobDAO();
                List<Job> jobList = dao.getAllJobs();
                if (jobList != null && !jobList.isEmpty()) {
                    for (Job job : jobList) {
            %>
            <tr>
                <td><%= job.getJobId() %></td>
                <td><a href="JobDetailsServlet?job_id=<%= job.getJobId() %>"><%= job.getJobTitle() %></a></td>
                <td><%= job.getJobCompany() %></td>
                <td><%= job.getJobLocation() %></td>
                <td><%= job.getJobSkills() %></td>
                <td>
                    <!-- Edit Button -->
                    <a href="EditJobServlet?jobId=<%= job.getJobId() %>" class="manage-cta-btn">Edit</a>

                    <!-- Delete Button -->
                 <form action="DeleteJobServlet" method="post" style="display:inline;">
                    <input type="hidden" name="jobId" value="<%= job.getJobId() %>">
                    <button type="submit" class="delete-btn">Delete</button>
                </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" style="text-align: center; color: #999;">No jobs found.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</section>
   

 	<footer>
            <p>&copy; 2025 KaamKaro. All rights reserved.</p>
        </footer>

		
</body>
</html>
