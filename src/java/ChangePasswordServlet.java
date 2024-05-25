import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
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

        // Retrieve form data
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        // Validate form data
        if (currentPassword == null || currentPassword.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            response.sendRedirect("changePass.jsp?error=Please+fill+all+fields");
            return;
        }

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/pawsome";
        String dbUser = "root";
        String dbPassword = "";

        // SQL query to retrieve current password
        String sqlRetrievePassword = "SELECT password FROM users WHERE email = ?";

        // SQL query to update password
        String sqlUpdatePassword = "UPDATE users SET password = ? WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement retrievePasswordStmt = connection.prepareStatement(sqlRetrievePassword);
             PreparedStatement updatePasswordStmt = connection.prepareStatement(sqlUpdatePassword)) {

            // Retrieve current password from database
            retrievePasswordStmt.setString(1, email);
            ResultSet resultSet = retrievePasswordStmt.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                // Check if current password matches stored password
                if (!currentPassword.equals(storedPassword)) {
                    response.sendRedirect("changePass.jsp?error=Incorrect+current+password");
                    return;
                }

                // Update password in database
                updatePasswordStmt.setString(1, newPassword);
                updatePasswordStmt.setString(2, email);
                int rowsUpdated = updatePasswordStmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Password updated successfully.");
                    response.sendRedirect("account.jsp?success=Password+updated+successfully");
                } else {
                    response.sendRedirect("account.jsp?error=Unable+to+update+password");
                }
            } else {
                // User not found
                response.sendRedirect("changePass.jsp?error=User+not+found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection problem", e);
        }
    }
}
