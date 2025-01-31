package contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate user
        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticateUser(username, password);
       
        if (user != null) {
            // Successful login, create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);          

            // Redirect to dashboard
            response.sendRedirect("userDashboard.jsp");
        } else {
            // Login failed
            request.setAttribute("error", "Invalid email or password. Please try again.");
            request.getRequestDispatcher("userLogin.jsp").forward(request, response);
        }
    }
}
