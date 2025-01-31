package contoller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import model.Job;

@WebServlet("/manage")
public class ManageJobServlet extends HttpServlet {

    // Handle POST requests for adding, updating, and deleting jobs
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the admin is logged in
        HttpSession session = request.getSession(false); // Get the current session, or null if there's no session
        if (session == null || session.getAttribute("adminUsername") == null) {
            // If there's no session or the admin is not logged in, redirect to login page
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        // Get the action parameter to determine the action (add, update, delete, etc.)
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Create a new Job object and set its fields from the form
            Job job = new Job();
            job.setJobTitle(request.getParameter("jobTitle"));
            job.setJobDescription(request.getParameter("jobDescription"));
            job.setJobCompany(request.getParameter("jobCompany"));
            job.setJobLocation(request.getParameter("jobLocation"));
            job.setJobSkills(request.getParameter("jobSkills"));
            job.setJobPostedDate(new Timestamp(System.currentTimeMillis()));

            // Call the DAO method to add the job
            JobDAO dao=new JobDAO();
            boolean result = dao.addJob(job);
            if (result ) {
                response.sendRedirect("manageJobs.jsp"); // Redirect to the job list page after successful addition
            } else {
                response.getWriter().println("Error adding job.");
            }
        }
        // Handle other actions like update, delete, etc.
    }

    // Handle GET requests for listing jobs
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the admin is logged in
        HttpSession session = request.getSession(false); // Get the current session
        if (session == null || session.getAttribute("adminUsername") == null) {
            // If the admin is not logged in, redirect to login page
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        // Get the action parameter for different functionalities
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            // Get all jobs from the database
        	JobDAO dao=new JobDAO();
            List<Job> jobs = dao.getAllJobs();
            request.setAttribute("jobs", jobs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageJobs.jsp");
            dispatcher.forward(request, response); // Forward to the job list page
        }
        // Add other actions like delete or view job details
    }

    
    
}
