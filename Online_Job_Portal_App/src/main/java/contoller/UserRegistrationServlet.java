package contoller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
  
        // Create a User object
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // You may want to hash the password for security
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Save the user to the database
        UserDAO userDAO = new UserDAO();
        boolean isRegistered = userDAO.registerUser(user);

        if (isRegistered) {
            // Registration successful, redirect to login page
            response.sendRedirect("login.html");
        } else {
            // Registration failed, show error message
            request.setAttribute("errorMessage", "Registration failed. Try again!");
            request.getRequestDispatcher("register.html").forward(request, response);
        }
    }
}
