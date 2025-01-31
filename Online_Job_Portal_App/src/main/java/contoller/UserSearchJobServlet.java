package contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import model.Job;

@WebServlet("/UserSearchJobServlet")
public class UserSearchJobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	 // Get session object
        HttpSession session = request.getSession(false);

        // Check if the user is logged in (admin session validation)
        if (session.getAttribute("user") == null) {
            response.sendRedirect("userLogin.jsp?error=Please login to access this page.");
            return;
        }

        // Get the search query from the request parameter
        String search = request.getParameter("query");

        // Instantiate the DAO to fetch filtered jobs
        JobDAO jobDAO = new JobDAO();
        List<Job> jobList = jobDAO.getJobsByFilters(search);

        // Pass the search results and the query back to the JSP
        request.setAttribute("jobList", jobList);
        request.setAttribute("search", search);

        // Forward the request to the JSP for display
        request.getRequestDispatcher("userSearchJob.jsp").forward(request, response);
    }
}
