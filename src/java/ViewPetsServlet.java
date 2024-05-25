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

@WebServlet("/ViewPetsServlet")
public class ViewPetsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pawsome";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Map<String, Object>> pets = new HashMap<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM pets")) {

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

        // Debugging: Print out the retrieved pets
        System.out.println("Retrieved pets: " + pets);

        request.setAttribute("pets", pets);

        // Logging before forwarding
        System.out.println("Forwarding to pets.jsp");
        request.getRequestDispatcher("pets.jsp").forward(request, response);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}