package contoller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.JobApplicationDAO;

@WebServlet("/adminViewApplicantFormData")
public class AdminViewApplicantFormDataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the admin is logged in
        if (request.getSession(false).getAttribute("adminId") == null) {
            response.sendRedirect("adminLogin.jsp?error=Please login to access the dashboard.");
            return;
        }

        // Get the applicant ID from the request (assuming it's passed as a parameter)
        int applicantId = Integer.parseInt(request.getParameter("applicantId"));
        String companyname = request.getParameter("companyname");

        // DAO to retrieve applicant form data
        JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();

        Map<String, List<JSONObject>> applicantformdata = new JobApplicationDAO().getApplicantFormData(applicantId,companyname);
            
       

        // Set the applicationsByCompany in the request scope to forward to JSP
        request.setAttribute("applicantformdata", applicantformdata);

        // Forward the request to the JSP page for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminViewApplicantFormData.jsp");
        dispatcher.forward(request, response);
    }
}
