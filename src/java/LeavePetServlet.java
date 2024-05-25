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

@WebServlet("/LeavePetServlet")
public class LeavePetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pawsome";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        boolean available = true; // Setting availability by servlet

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Prepare SQL statement
                String sql = "INSERT INTO pets (name, age, species, breed, description, img, available) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set parameters
                    statement.setString(1, name);
                    statement.setInt(2, age);
                    statement.setString(3, species);
                    statement.setString(4, breed);
                    statement.setString(5, description);
                    statement.setString(6, img);
                    statement.setBoolean(7, available);

                    // Execute the statement
                    statement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions
            throw new ServletException("Database connection problem", e);
        }

        // Redirect to a success page or any other appropriate page
        response.sendRedirect("adopt.jsp");
    }
}
