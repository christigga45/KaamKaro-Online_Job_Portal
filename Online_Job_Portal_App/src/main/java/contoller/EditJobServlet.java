package contoller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import model.Job;

@WebServlet("/EditJobServlet")
public class EditJobServlet extends HttpServlet {
    
    // Handle GET request to display the job edit form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session object
        HttpSession session = request.getSession(false);

        // Check if the user is logged in (admin session validation)
        if (session.getAttribute("adminUsername") == null) {
            response.sendRedirect("adminLogin.jsp?error=Please login to access this page.");
            return;
        }

        int jobId = Integer.parseInt(request.getParameter("jobId"));
        
        // Fetch job details using the DAO method
        JobDAO jobDAO = new JobDAO();
        Job job = jobDAO.getJobById(jobId);

        // If job not found, redirect to manage jobs page with error message
        if (job == null) {
            request.setAttribute("errorMessage", "Job not found!");
            response.sendRedirect("manageJobs.jsp");
            return;
        }

        // Set the job object as a request attribute to display in the JSP
        request.setAttribute("job", job);
        
        // Forward to the edit job JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("editJob.jsp");
        dispatcher.forward(request, response);
    }

    // Handle POST request to update the job details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session object
        HttpSession session = request.getSession(false);

        // Check if the user is logged in (admin session validation)
        if (session.getAttribute("adminUsername") == null) {
            response.sendRedirect("adminLogin.jsp?error=Please login to access this page.");
            return;
        }

        // Get parameters from the form
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");
        String jobCompany = request.getParameter("jobCompany");
        String jobLocation = request.getParameter("jobLocation");
        String jobSkills = request.getParameter("jobSkills");
        Timestamp jobPostedDate = Timestamp.valueOf(request.getParameter("jobPostedDate"));

        // Create a Job object with the new details
        Job job = new Job(jobId, jobTitle, jobDescription, jobCompany, jobLocation, jobSkills, jobPostedDate);

        // Call the DAO method to update the job in the database
        JobDAO jobDAO = new JobDAO();
        boolean isUpdated = jobDAO.updateJob(job);

        // If update is successful, redirect to the manage jobs page
        if (isUpdated) {
            session.setAttribute("msg", "Job updated successfully!");
            response.sendRedirect("manageJobs.jsp");
        } else {
            // If update fails, set error message and redirect to edit page
            session.setAttribute("msg", "Failed to update job.");
            response.sendRedirect("editJob.jsp?jobId=" + jobId);
        }
    }
}

