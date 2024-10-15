import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String rollno = request.getParameter("rollno");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if (!password.equals(confirmPassword)) {
            response.getWriter().println("Passwords do not match!");
            return;
        }
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO crudop(name, rollno, username, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, rollno);
            ps.setString(3, username);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, password);
            ps.executeUpdate();
            ps.close();
            response.getWriter().println("Registration successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
