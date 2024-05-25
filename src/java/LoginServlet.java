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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
        // Retrieve login credentials
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/pawsome";
        String dbUser = "root";
        String dbPassword = "";

        // SQL query to validate user
        // SQL query to validate user
        String sql = "SELECT session_id, user_id, name FROM users WHERE email = ? AND password = ?";

        
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // User found, create session
                    int sessionId = resultSet.getInt("session_id");
                    int userId = resultSet.getInt("user_id");
                    String name = resultSet.getString("name");

                    HttpSession session = request.getSession();
                    session.setAttribute("sessionId", sessionId);
                    session.setAttribute("userId", userId);
                    session.setAttribute("name", name);
                    session.setAttribute("email", email);
                    

                    // Redirect to welcome page
                    response.sendRedirect("welcome.jsp");
                } else {
                    // User not found, redirect to login with error message
                    response.sendRedirect("login.html?error=Invalid+credentials");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection problem", e);
        }
    }
}
