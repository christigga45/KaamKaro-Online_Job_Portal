package contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import model.Job;

@WebServlet("/AddJobServlet")
public class AddJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Start or retrieve the session
        HttpSession session = request.getSession(false);

        // Check if the session exists and the admin is logged in
        if (session == null || session.getAttribute("adminUsername") == null) {
            // Redirect to login page if the admin is not logged in
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        // Get parameters from the form
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");
        String companyName = request.getParameter("jobCompany");
        String location = request.getParameter("jobLocation");
        String skills = request.getParameter("jobSkills");

        // Create a Job object from the form data
        Job job = new Job();
        job.setJobTitle(jobTitle);
        job.setJobDescription(jobDescription);
        job.setJobCompany(companyName);
        job.setJobLocation(location);
        job.setJobSkills(skills);

        // Create a JobDAO object and call addJob() to insert the job into the database
        JobDAO jobDAO = new JobDAO();
        boolean success = jobDAO.addJob(job);

        // Redirect based on success or failure
        if (success) {
        	session.setAttribute("msg", "Job Added Successfully !!!");
            RequestDispatcher rd=request.getRequestDispatcher("manageJobs.jsp");
            rd.forward(request, response);
        } else {
            response.getWriter().println("Failed to add job listing.");
        }
    }
}
