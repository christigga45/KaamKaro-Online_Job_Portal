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

@WebServlet("/adminSearchJobServlet")
public class AdminSearchJobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session=request.getSession(false);
    	if (session == null || session.getAttribute("adminUsername") == null) {
            // Redirect to login page if the admin is not logged in
            response.sendRedirect("adminLogin.jsp");
            return;
        }
        // Get the search query from the request parameter
        String search = request.getParameter("query");

        // Instantiate the DAO to fetch filtered jobs
        JobDAO jobDAO = new JobDAO();
        List<Job> jobList = jobDAO.getJobsByFilters(search);

        // Pass the search results and the query back to the JSP
        request.setAttribute("jobList", jobList);
        request.setAttribute("query", search);

        // Forward the request to the JSP for display
        request.getRequestDispatcher("searchJob.jsp").forward(request, response);
    }
}
