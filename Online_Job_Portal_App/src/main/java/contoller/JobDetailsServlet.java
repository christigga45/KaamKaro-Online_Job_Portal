package contoller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import model.Job;

@WebServlet("/JobDetailsServlet")
public class JobDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	 HttpSession session = request.getSession(false);
    	if (session == null || session.getAttribute("adminUsername") == null) {
            // Redirect to login page if the admin is not logged in
            response.sendRedirect("adminLogin.jsp");
            return;
        }
        // Get job_id from request
        int jobId =Integer.parseInt(request.getParameter("job_id")) ;

        // Redirect if job_id is missing
        if (jobId == -1) {
            response.sendRedirect("manageJobs.jsp?error=Job not found.");
            return;
        }

        // Fetch job details from the database
        JobDAO jobDAO = new JobDAO();
       
            Job jobListing = jobDAO.getJobById(jobId);
            if (jobListing != null) {
                // Set job details as request attributes
                request.setAttribute("jobListing", jobListing);

                // Forward to the JSP page
                request.getRequestDispatcher("jobPage.jsp").forward(request, response);
            } else {
                response.sendRedirect("manageJobs.jsp?error=Job not found.");
            }
        } 
    }

