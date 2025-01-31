<%@page import="model.User"%>
<%@page import="model.Job"%>
<%@page import="java.util.List"%>
<%
User user=(User)session.getAttribute("user");
if ( user == null) {
    response.sendRedirect("userLogin.jsp?error=Please login to access the dashboard.");
    return;
}
    String query = (String) request.getAttribute("search");
	List<Job> jobList = (List<Job>) request.getAttribute("jobList");
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
<div class="navbar" >
    <div class="container">
        <div class="logo">KaamKaro</div>
        <ul class="nav-links">
            <li><a href="userDashboard.jsp">Dashboard</a></li>
            <li><a href="userLogout">Logout</a></li>
        </ul>
    </div>
</div>

<div class="manage-jobs">
    <div class="manage-container">
        <h2>Search Jobs</h2>
        <!-- Search Form -->
        <form action="UserSearchJobServlet" method="get" class="search-form">
            <input type="text" name="query" value="<%= query != null ? query : "" %>" 
                   placeholder="Search Jobs by skill, location, or company" class="search-input">
            <button type="submit" class="search-btn">Search</button>
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
            </tr>
            </thead>
            <tbody>
            <%
                if (jobList != null && !jobList.isEmpty()) {
                    for (Job job : jobList) {
            %>
            <tr>
                <td><%= job.getJobId() %></td>
                <td><a href="userJobDetailsServlet?job_id=<%= job.getJobId() %>"><%= job.getJobTitle() %></a></td>
                <td><%= job.getJobCompany() %></td>
                <td><%= job.getJobLocation() %></td>
                <td><%= job.getJobSkills() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align: center; color: #999;">No jobs found matching your search.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<footer>
    <p>&copy; 2025 KaamKaro. All rights reserved.</p>
</footer>
</body>
</html>
