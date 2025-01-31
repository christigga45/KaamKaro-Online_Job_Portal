<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="container">
            <div class="logo">KaamKaro</div>
            <ul class="nav-links">
                <li><a href="index.html">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div>
    </nav>

    <!-- Login Form -->
    <section class="login-section">
        <form action="AdminLoginServlet" method="post" class="login-form">
            <h2>Admin Login</h2>

            <!-- Display error message if present -->
            <p style="color: red;">
                <% 
                   if (request.getParameter("error") != null) { 
                       out.print(request.getParameter("error"));
                   } 
                %>
            </p>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit" class="cta-btn">Login</button>
        </form>
    </section>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 KaamKaro. All rights reserved.</p>
    </footer>
</body>
</html>
