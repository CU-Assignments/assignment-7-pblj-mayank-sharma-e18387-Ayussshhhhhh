import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Simple static validation for demo purposes
        if ("user".equals(username) && "pass".equals(password)) {
            // Valid credentials
            response.sendRedirect("welcome.jsp");
        } else {
            // Invalid credentials
            response.sendRedirect("login.html");
        }
    }
}
