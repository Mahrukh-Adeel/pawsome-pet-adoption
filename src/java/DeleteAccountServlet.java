import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Load the MySQL driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current session
        HttpSession session = request.getSession(false);
        if (session == null) {
            // No session found, return error
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("No session found");
            return;
        }

        // Get the user's email from the session
        String email = (String) session.getAttribute("email");
        if (email == null) {
            // No email found in session, return error
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("No email found in session");
            return;
        }

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/pawsome";
        String dbUser = "root";
        String dbPassword = "";

        // SQL query to delete user
        String sql = "DELETE FROM users WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("User account deleted successfully.");
                // Invalidate the session to log the user out
                session.invalidate();
                // Return success
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                // User not found, return error
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("User not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Database error, return error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Database connection problem");
        }
    }
}
