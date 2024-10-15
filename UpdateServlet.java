import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rollno = request.getParameter("rollno").trim();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        PrintWriter out = response.getWriter();
        if (!password.equals(confirmPassword)) {
            out.println("Passwords do not match!");
            return;
        }
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement("SELECT COUNT(*) FROM users1 WHERE rollno = ?");
            ps.setString(1, rollno);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                ps = connection.prepareStatement(
                        "UPDATE crudop SET username = ?, email = ?, phone = ?, password = ? WHERE rollno = ?");
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, password);
                ps.setString(5, rollno);
                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    out.println("Records updated successfully!");
                } else {
                    out.println("No records found with Roll No: " + rollno);
                }
            } else {
                out.println("No records found with Roll No: " + rollno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("An error occurred while updating records: " + e.getMessage());
        }
    }
}
