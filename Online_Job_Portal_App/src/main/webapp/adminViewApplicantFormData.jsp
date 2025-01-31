<%@page import="org.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%

if (session.getAttribute("adminId") == null) {
    response.sendRedirect("adminLogin.jsp?error=Please login to access the dashboard.");
    return;
}
    // Retrieve the grouped application data from the request
    Map<String, List<JSONObject>> applicantformdata = (Map<String, List<JSONObject>>) request.getAttribute("applicantformdata");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Applicant Resume</title>
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

    <div class="view-applications">
        <div class="manage-container">
            <h2>Applicant Resume</h2>

            <% 
                // Check if there are any applications grouped by company
                if (applicantformdata != null && !applicantformdata.isEmpty()) {
                    // Iterate through the map to display applications by company
                    for (Map.Entry<String, List<JSONObject>> entry : applicantformdata.entrySet()) {
                        String companyName = entry.getKey();
                        List<JSONObject> formDataList = entry.getValue();
            %>

            <h3>Company: <%= companyName %></h3>

            <% 
                // Loop through each form data for the company
                for (JSONObject formDataJson : formDataList) {
            %>

            <!-- Table to display the resume-like structure -->
            <table class="resume-table">
                <thead>
                    <tr>
                        <th>Field</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // Iterate over each key in the JSONObject and display it in the table
                        for (String key : formDataJson.keySet()) {
                            String value = formDataJson.getString(key);  // Get the value for the key
                    %>
                    <tr>
                        <td><strong><%= key %>:</strong></td>
                        <td><%= value %></td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>

            <% 
                    }
                }} else {
            %>
            <p>No application data found for this applicant.</p>
            <% } %>
        </div>
    </div>

    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>

</body>
</html>
