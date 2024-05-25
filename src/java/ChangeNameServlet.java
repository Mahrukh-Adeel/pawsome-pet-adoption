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

@WebServlet("/ChangeNameServlet")
public class ChangeNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Load the MySQL driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current session
        HttpSession session = request.getSession(false);
        if (session == null) {
            // No session found, redirect to login
            response.sendRedirect("login.html");
            return;
        }

        // Get the user's email from the session
        String email = (String) session.getAttribute("email");
        if (email == null) {
            // No email found in session, redirect to login
            response.sendRedirect("login.html");
            return;
        }

        // Retrieve the new name from the request
        String newName = request.getParameter("newName");
        if (newName == null || newName.trim().isEmpty()) {
            // Invalid name, redirect back to profile page with an error message
            response.sendRedirect("account.jsp?error=Invalid+name");
            return;
        }

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/pawsome";
        String dbUser = "root";
        String dbPassword = "";

        // SQL query to update the user's name
        String sql = "UPDATE users SET name = ? WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setString(2, email);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("User name updated successfully.");
                // Update the session attribute with the new name
                session.setAttribute("name", newName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection problem", e);
        }

        // Redirect to profile page or a confirmation page
        response.sendRedirect("account.jsp?success=Name+updated+successfully");
    }
}
