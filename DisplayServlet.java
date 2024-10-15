import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class DisplayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String rollno = request.getParameter("rollno");
        out.println("<p>Roll No: " + rollno + "</p>");
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM crudop WHERE rollno = ?");
            ps.setString(1, rollno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                out.println(
                        "<table border='1'><tr><th>Name</th><th>Roll No</th><th>Username</th><th>Email</th><th>Phone</th></tr>");
                out.println("<tr><td>" + rs.getString("name") + "</td><td>" + rs.getString("rollno") + "</td><td>"
                        + rs.getString("username") + "</td><td>" + rs.getString("email") + "</td><td>"
                        + rs.getString("phone") + "</td></tr>");
                out.println("</table>");
            } else {
                out.println("<p>No user found with Roll No: " + rollno + "</p>");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error retrieving user details!!!</p>");
        } finally {
            out.close();
        }
    }
}
