package contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobApplicationDAO;

@WebServlet("/updateApplicationStatus")
public class UpdateApplicationStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	 // Get session object
        HttpSession session = request.getSession(false);

        // Check if the user is logged in (admin session validation)
        if (session.getAttribute("adminUsername") == null) {
            response.sendRedirect("adminLogin.jsp?error=Please login to access this page.");
            return;
        }

        // Retrieve application ID and the new status from the form
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        String status = request.getParameter("status");

        // Initialize DAO to update application status
        JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
        boolean updated = jobApplicationDAO.updateApplicationStatus(applicationId, status);

        // If the status was successfully updated, redirect to the applications page
        if (updated) {
            response.sendRedirect("adminViewApplications");  // Redirects to the admin view page to see updated status
        } else {
            response.sendRedirect("error.jsp?error=Failed to update application status");
        }
    }
}
