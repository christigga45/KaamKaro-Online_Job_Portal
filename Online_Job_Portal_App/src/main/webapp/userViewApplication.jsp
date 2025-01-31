<%@page import="model.User"%>
<%@page import="dao.JobDAO"%>
<%@page import="dao.JobApplicationDAO, model.Job, model.JobApplication, java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>

<%
// Assuming the user is already logged in and their details are stored in session
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("userLogin.jsp?error=Please login to access the dashboard.");
	return;
}

// Initialize DAO to get job applications


// Get all job applications for the current user
List<JobApplication> jobApplications = (List<JobApplication>)request.getAttribute("jobApplications");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Applications</title>
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

	<div class="view-applications">
		<div class="manage-container">
			<h2>My Job Applications</h2>

			<%
			// Check if the user has applied for any jobs
			if (jobApplications != null && !jobApplications.isEmpty()) {
			%>

			<table class="styled-table">
				<thead>
					<tr>
						<th>Job Title</th>
						<th>Company</th>
						<th>Location</th>
						<th>Skills</th>
						<th>Application Status</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (JobApplication applicat : jobApplications) {
						Job job = new JobDAO().getJobById(applicat.getJobId()); // Get the job details using jobId
					%>
					<tr>
						<td><a
							href="userJobDetailsServlet?job_id=<%=job.getJobId()%>"><%=job.getJobTitle()%></a></td>
						<td><%=job.getJobCompany()%></td>
						<td><%=job.getJobLocation()%></td>
						<td><%=job.getJobSkills()%></td>
						<td><%=applicat.getApplicationStatus()%></td>
						<!-- Display the application status -->
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<%
			} else {
			%>
			<p>You have not applied for any jobs yet.</p>
			<%
			}
			%>
		</div>
	</div>

	<footer>
		<p>&copy; 2025 KaamKaro. All rights reserved.</p>
	</footer>

</body>
</html>
