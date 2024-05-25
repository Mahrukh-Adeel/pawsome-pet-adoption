import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewMyPetsServlet")
public class ViewMyPetsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pawsome";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login.html");
            return;
        }

        Map<Integer, Map<String, Object>> pets = new HashMap<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM pets WHERE user_id = ?")) {

                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Map<String, Object> pet = new HashMap<>();
                        int petId = resultSet.getInt("pet_id");
                        pet.put("name", resultSet.getString("name"));
                        pet.put("age", resultSet.getInt("age"));
                        pet.put("species", resultSet.getString("species"));
                        pet.put("description", resultSet.getString("description"));
                        pet.put("img", resultSet.getString("img"));
                        pet.put("available", resultSet.getBoolean("available"));
                        pets.put(petId, pet);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Set the retrieved pets as a request attribute
        request.setAttribute("myPets", pets);

        // Forward the request to the JSP to display the pets
        request.getRequestDispatcher("yours.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
