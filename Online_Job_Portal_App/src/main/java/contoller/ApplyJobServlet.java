package contoller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.JobApplicationDAO;
import model.JobApplication;
import model.User;

@WebServlet("/ApplyJobServlet")
public class ApplyJobServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        HttpSession session = request.getSession(false);
        User user=(User) session.getAttribute("user");
        if (session == null ||  user== null) {
            // Redirect to login page if no session or user attribute found
            response.sendRedirect("userLogin.jsp?error=Please Login again.");
            return; // Stop further processing
        }

       
        // Retrieve applicant ID and job ID from session or form
        int applicantId = Integer.parseInt(request.getParameter("userId")) ;
        int jobId = Integer.parseInt(request.getParameter("jobId"));

        // Retrieve form data
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String education = request.getParameter("education");
        String projects = request.getParameter("projects");
        String experience = request.getParameter("experience");
        String skills = request.getParameter("skills");

        // Create a JSON object for form data
        JSONObject formDataJson = new JSONObject();
        formDataJson.put("FullName", fullName);
        formDataJson.put("Email", email);
        formDataJson.put("Phone", phone);
        formDataJson.put("Education", education);
        formDataJson.put("Projects", projects);
        formDataJson.put("Experience", experience);
        formDataJson.put("Skills", skills);

        // Prepare the JobApplication object
        JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicantId(applicantId);
        jobApplication.setJobId(jobId);
        jobApplication.setApplicationStatus("Pending");
        jobApplication.setApplicationDate(new Timestamp(System.currentTimeMillis()));
        jobApplication.setFormData(formDataJson.toString());

        // Save application to database
        try {
            JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
            boolean success = jobApplicationDAO.saveJobApplication(jobApplication);
     
            if (success) {
            	request.setAttribute("user", user);
                response.sendRedirect("browseJobs.jsp");
            } else {
                response.sendRedirect("browseJobs.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("userLogin.jsp?error=Invalid username or password");
        }
    }
}
