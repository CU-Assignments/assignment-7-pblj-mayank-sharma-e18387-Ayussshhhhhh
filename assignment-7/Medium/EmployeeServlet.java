import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class EmployeeServlet extends HttpServlet {

    private String dbUrl, dbUser, dbPassword;

    public void init() throws ServletException {
        try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/db-config.properties")) {
            Properties props = new Properties();
            props.load(input);
            Class.forName(props.getProperty("db.driver"));
            dbUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.user");
            dbPassword = props.getProperty("db.password");
        } catch (Exception e) {
            throw new ServletException("DB config error", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("empId");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = (empId != null && !empId.isEmpty())
                    ? "SELECT * FROM employees WHERE id = ?"
                    : "SELECT * FROM employees";
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (empId != null && !empId.isEmpty()) stmt.setInt(1, Integer.parseInt(empId));
            ResultSet rs = stmt.executeQuery();

            out.println("<h2>Employee Records</h2>");
            while (rs.next()) {
                out.println("<p>ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Department: " + rs.getString("department") +
                            ", Email: " + rs.getString("email") + "</p>");
            }
            out.println("<a href='employeeList.html'>Back</a>");
        } catch (Exception e) {
            throw new ServletException("DB error", e);
        }
    }
}
