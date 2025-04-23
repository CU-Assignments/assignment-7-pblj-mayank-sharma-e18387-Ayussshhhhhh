import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "user", "pass")) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO attendance(student_id, name, date, status) VALUES (?, ?, ?, ?)");
            ps.setInt(1, studentId);
            ps.setString(2, name);
            ps.setString(3, date);
            ps.setString(4, status);
            ps.executeUpdate();
            response.sendRedirect("attendance-success.jsp");
        } catch (Exception e) {
            throw new ServletException("DB error", e);
        }
    }
}
