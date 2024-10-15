import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM crudop WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("home.html");
            } else {
                response.getWriter().println("Invalid login credentials!");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
