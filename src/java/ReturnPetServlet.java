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

@WebServlet("/ReturnPetServlet")
public class ReturnPetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pawsome";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String petIdStr = request.getParameter("petId");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (petIdStr == null || petIdStr.isEmpty()) {
            response.getWriter().write("invalid_pet_id");
            return;
        }

        if (userId == null) {
            response.getWriter().write("not_logged_in");
            return;
        }

        try {
            int petId = Integer.parseInt(petIdStr);

            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE pets SET available = 1, user_id = NULL WHERE pet_id = ? AND user_id = ?")) {
                statement.setInt(1, petId);
                statement.setInt(2, userId);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    response.getWriter().write("success");
                    response.sendRedirect("return.jsp");
                } else {
                    response.getWriter().write("fail");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("sql_error: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write("invalid_pet_id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("class_not_found_error");
        }
    }
}
