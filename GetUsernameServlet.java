import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class GetUsernameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (username != null) {
            out.print(username);
        } else {
            out.print("Guest");
        }
    }
}
