package com.soumikservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HelloServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String gender = request.getParameter("gender");
        String mobilenumber = request.getParameter("mobilenumber");
        String action = request.getParameter("action");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Details</title>");
        out.println("<style>");
        out.println("body { font-family: 'Montserrat', sans-serif; background: linear-gradient(135deg, #71b7e6, #9b59b6); color: #333; text-align: center; padding: 50px; }");
        out.println(".container { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1); display: inline-block; width: 600px; }");
        out.println("h1 { margin-bottom: 20px; color: #9b59b6; }");
        out.println("p { margin: 10px 0; font-size: 16px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Registration Details</h1>");

        String jdbcURL = "jdbc:postgresql://localhost:5432/employee";
        String dbUser = "postgres";
        String dbPassword = "123";

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                switch (action.toLowerCase()) {
                    case "create":
                        createRecord(connection, id, name, ageStr, gender, mobilenumber, out);
                        break;
                    case "update":
                        updateRecord(connection, id, name, ageStr, gender, mobilenumber, out);
                        break;
                    case "delete":
                        deleteRecord(connection, id, out);
                        break;
                    case "show":
                        showRecord(connection, id, out);
                        break;
                    case "showall":
                        showAllRecords(connection, out);
                        break;
                    default:
                        out.println("<p>Invalid action</p>");
                }
            }
        } catch (ClassNotFoundException e) {
            out.println("<p>PostgreSQL JDBC Driver not found. Include it in your library path.</p>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            e.printStackTrace(out); // Print error details to the response
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    private void createRecord(Connection connection, String id, String name, String ageStr, String gender, String mobilenumber, PrintWriter out) throws SQLException {
        int age = Integer.parseInt(ageStr);
        String sql = "INSERT INTO users (id, name, age, gender, mobilenumber) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setString(4, gender);
            statement.setString(5, mobilenumber);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<p>Record inserted successfully!</p>");
                out.println("<p><strong>ID:</strong> " + id + "</p>");
                out.println("<p><strong>Name:</strong> " + name + "</p>");
                out.println("<p><strong>Age:</strong> " + age + "</p>");
                out.println("<p><strong>Gender:</strong> " + gender + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + mobilenumber + "</p>");
            } else {
                out.println("<p>Failed to insert the record.</p>");
            }
        }
    }

    private void updateRecord(Connection connection, String id, String name, String ageStr, String gender, String mobilenumber, PrintWriter out) throws SQLException {
        int age = Integer.parseInt(ageStr);
        String sql = "UPDATE users SET name = ?, age = ?, gender = ?, mobilenumber = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender);
            statement.setString(4, mobilenumber);
            statement.setString(5, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                out.println("<p>Record updated successfully!</p>");
                out.println("<p><strong>ID:</strong> " + id + "</p>");
                out.println("<p><strong>Name:</strong> " + name + "</p>");
                out.println("<p><strong>Age:</strong> " + age + "</p>");
                out.println("<p><strong>Gender:</strong> " + gender + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + mobilenumber + "</p>");
            } else {
                out.println("<p>Failed to update the record.</p>");
            }
        }
    }

    private void deleteRecord(Connection connection, String id, PrintWriter out) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                out.println("<p>Record deleted successfully!</p>");
                out.println("<p><strong>ID:</strong> " + id + "</p>");
            } else {
                out.println("<p>Failed to delete the record.</p>");
            }
        }
    }

    private void showRecord(Connection connection, String id, PrintWriter out) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                out.println("<p><strong>ID:</strong> " + resultSet.getString("id") + "</p>");
                out.println("<p><strong>Name:</strong> " + resultSet.getString("name") + "</p>");
                out.println("<p><strong>Age:</strong> " + resultSet.getInt("age") + "</p>");
                out.println("<p><strong>Gender:</strong> " + resultSet.getString("gender") + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + resultSet.getString("mobilenumber") + "</p>");
            } else {
                out.println("<p>No record found with ID: " + id + "</p>");
            }
        }
    }

    private void showAllRecords(Connection connection, PrintWriter out) throws SQLException {
        String sql = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                out.println("<p><strong>ID:</strong> " + resultSet.getString("id") + "</p>");
                out.println("<p><strong>Name:</strong> " + resultSet.getString("name") + "</p>");
                out.println("<p><strong>Age:</strong> " + resultSet.getInt("age") + "</p>");
                out.println("<p><strong>Gender:</strong> " + resultSet.getString("gender") + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + resultSet.getString("mobilenumber") + "</p>");
                out.println("<hr>");
            }
        }
    }
}
