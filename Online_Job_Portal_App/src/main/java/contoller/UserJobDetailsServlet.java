package contoller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobApplicationDAO;
import dao.JobDAO;
import model.Job;
import model.User;

@WebServlet("/userJobDetailsServlet")
public class UserJobDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get job_id from request
        int jobId = Integer.parseInt(request.getParameter("job_id"));
        
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("userLogin.jsp?error=Please login to apply for a job.");
            return;
        }

        // Fetch job details from the database
        JobDAO jobDAO = new JobDAO();
        try {
            Job jobListing = jobDAO.getJobById(jobId);
          
            if (jobListing != null) {
                // Check if the user has already applied for the job
                boolean hasApplied;
				
					hasApplied = new JobApplicationDAO().hasUserAppliedForJob(user.getApplicantId(), jobId);
			 

                // Set job details and apply status as request attributes
                request.setAttribute("jobListing", jobListing);
                request.setAttribute("hasApplied", hasApplied);

                // Forward to the JSP page
                request.getRequestDispatcher("userJobPage.jsp").forward(request, response);
            } else {
                response.sendRedirect("browseJobs.jsp?error=Job not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("manageJobs.jsp?error=Error retrieving job details.");
        }
    }
}
