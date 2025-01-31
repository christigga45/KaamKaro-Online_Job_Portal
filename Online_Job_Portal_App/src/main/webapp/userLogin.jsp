<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <div class="container">
            <div class="logo">KaamKaro</div>
            <ul class="nav-links">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="register.html">Register</a></li>
                <li><a href="userLogin.jsp" class="active">Login</a></li>
            </ul>
        </div>
    </div>

    <!-- Login Section -->
    <section class="login-section">
        <form action="UserLoginServlet" method="post" class="login-form">
            <h2>Login</h2>   
            
             <!-- Display error message if present -->
            <p style="color: red;">
                <% 
                	String error=(String)request.getParameter("error");
                   if ( error!=null) { 
                       out.print(request.getParameter("error"));
                   } 
                %>
            </p>         
            
            <!-- Input Fields -->
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="Enter your username" required>
            
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="Enter your password" required>
            
            <!-- Submit Button -->
            <button type="submit" class="cta-btn">Login</button>
            
            <!-- Redirect to Registration -->
            <p>Don't have an account? <a href="register.html">Register here</a>.</p>
        </form>
    </section>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 KaamKaro. All Rights Reserved.</p>
    </footer>
</body>
</html>
