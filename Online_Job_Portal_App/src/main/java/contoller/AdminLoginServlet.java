package contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import model.Admin;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
            // Initialize AdminDAO
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = adminDAO.validateAdmin(username, password);
           
            if (admin != null) {
                // Valid admin credentials, create session and redirect
                HttpSession session = request.getSession();
                session.setAttribute("adminId", admin.getAdminId());
                session.setAttribute("adminUsername", admin.getUsername());
                response.sendRedirect("adminDashboard.jsp"); // Redirect to dashboard
            } else {
                // Invalid credentials, redirect back to login page with error
                response.sendRedirect("adminLogin.jsp?error=Invalid username or password");
            }
        
    }
}