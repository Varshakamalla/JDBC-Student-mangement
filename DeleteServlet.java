import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rollno = request.getParameter("rollno");
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM crudop WHERE rollno=?");
            ps.setString(1, rollno);
            int rowsDeleted = ps.executeUpdate();
            ps.close();
            if (rowsDeleted > 0) {
                response.getWriter().println("Record deletion successful!");
            } else {
                response.getWriter().println("No record found with that roll number!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
