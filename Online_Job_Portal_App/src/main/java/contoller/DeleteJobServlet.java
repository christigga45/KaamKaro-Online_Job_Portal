package contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;

@WebServlet("/DeleteJobServlet")
public class DeleteJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        HttpSession session = request.getSession(false);

        // Check if the user is logged in (admin session validation)
        if (session.getAttribute("adminUsername") == null) {
            response.sendRedirect("adminLogin.jsp?error=Please login to access this page.");
            return;
        }
        
        // Get the jobId from the request parameter
        String jobIdParam = request.getParameter("jobId");

        int jobId = 0;

        // Validate jobId parameter
        try {
            jobId = Integer.parseInt(jobIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("manageJobs.jsp?error=Invalid job ID.");
            return;
        }

        // Call DAO to delete the job
        JobDAO jobDAO = new JobDAO();
        boolean isDeleted = jobDAO.deleteJob(jobId);

        // Redirect based on deletion success/failure
        if (isDeleted) {
        	request.setAttribute("msg", "Job Deleted Successfully");
            response.sendRedirect("manageJobs.jsp?error=Job deleted successfully.");
        } else {
            response.sendRedirect("manageJobs.jsp?error=Failed to delete the job. Please try again.");
        }
    }
}
