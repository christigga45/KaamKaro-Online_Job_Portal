package contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JobApplicationDAO;
import model.JobApplication;
import model.User;

@WebServlet("/userViewApplicationServlet")
public class UserViewApplicationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int userId= Integer.parseInt(request.getParameter("userId"));
    	
        // Retrieve the applicantId from the session (logged-in user)
        User user = (User) request.getSession(false).getAttribute("user");

        if (user == null) {
            response.sendRedirect("userLogin.jsp?error=Please login to access your applications.");
            return;
        }

        // Initialize the DAO to fetch job applications
        JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
        
        // Retrieve job applications for the logged-in user
        List<JobApplication> jobApplications = jobApplicationDAO.getApplicationsByUser(userId);

        // Set the job applications in the request for the JSP to use
        request.setAttribute("jobApplications", jobApplications);

        // Forward the request to the JSP to display job applications
        RequestDispatcher dispatcher = request.getRequestDispatcher("userViewApplication.jsp");
        dispatcher.forward(request, response);
    }
}

