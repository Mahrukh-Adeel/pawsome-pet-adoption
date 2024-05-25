import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
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
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Generate a random session ID
        Random rand = new Random();
        int sessionId = rand.nextInt(999999999);

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/pawsome";
        String dbUser = "root";
        String dbPassword = "";

        // Insert user data into the database
        String sql = "INSERT INTO users (session_id, name, email, password) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, sessionId);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, password);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("A new user has been inserted successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection problem", e);
        }

        // Redirect to login page
        response.sendRedirect("login.html");
    }
}
